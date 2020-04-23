package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.Networking.BLUETOOTH
import com.utzcoz.emulator.device.generator.Networking.NFC
import com.utzcoz.emulator.device.generator.Networking.WIFI
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
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

    @ParameterizedTest
    @MethodSource("unsupportedNetworkingTypes")
    fun testGetNetworkingTypeWithUnsupportedType(type: String) {
        assertThrows<IllegalArgumentException>("Don't support networking $type") {
            Networking.getNetworkingType(type)
        }
    }

    companion object {
        @JvmStatic
        fun networkingTypes() = listOf(
            Arguments.of(NFC.type, NFC),
            Arguments.of(BLUETOOTH.type, BLUETOOTH),
            Arguments.of(WIFI.type, WIFI)
        )

        @JvmStatic
        fun unsupportedNetworkingTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(NFC.type + "-unsupported"),
            Arguments.of(BLUETOOTH.type + "-unsupported"),
            Arguments.of(WIFI.type + "-unsupported")
        )
    }
}