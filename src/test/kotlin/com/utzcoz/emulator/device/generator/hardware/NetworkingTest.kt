package com.utzcoz.emulator.device.generator.hardware

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class NetworkingTest {
    @ParameterizedTest
    @MethodSource("networkingTypes")
    fun testGetNetworkingTypeWithSupportedType(type: String, networking: Networking) {
        assertEquals(networking, Networking.getNetworkingType(type))
    }

    @ParameterizedTest
    @MethodSource("unsupportedNetworkingTypes")
    fun testGetNetworkingTypeWithUnsupportedType(type: String) {
        assertThrows<IllegalArgumentException> {
            Networking.getNetworkingType(type)
        }
    }

    companion object {
        @JvmStatic
        fun networkingTypes() =
            Networking.values().map { networking -> Arguments.of(networking.type, networking) }.toList()

        @JvmStatic
        fun unsupportedNetworkingTypes(): List<Arguments> {
            val result =
                Networking
                    .values()
                    .map { networking -> Arguments.of(networking.type + "-unsuppported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}