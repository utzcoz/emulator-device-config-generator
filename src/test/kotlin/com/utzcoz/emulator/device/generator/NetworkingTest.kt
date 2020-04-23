package com.utzcoz.emulator.device.generator

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class NetworkingTest {
    @ParameterizedTest
    @MethodSource("networkingTypes")
    fun testGetNetworkingTypeWithSupportedType(type: String, networking: Networking) {
        assertThat(Networking.getNetworkingType(type), hamis(networking))
    }

    companion object {
        @JvmStatic
        fun networkingTypes() = listOf(
            Arguments.of(Networking.NFC.type, Networking.NFC),
            Arguments.of(Networking.BLUETOOTH.type, Networking.BLUETOOTH),
            Arguments.of(Networking.WIFI.type, Networking.WIFI)
        )
    }
}