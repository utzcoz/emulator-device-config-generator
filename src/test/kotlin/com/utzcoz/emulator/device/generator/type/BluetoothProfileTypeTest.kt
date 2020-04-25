package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BluetoothProfileTypeTest {
    @ParameterizedTest
    @MethodSource("bluetoothProfileTypes")
    fun testGetBluetoothProfileTypeWithSupportedTypes(inputType: String, bluetoothProfileType: BluetoothProfileType) {
        assertEquals(bluetoothProfileType, BluetoothProfileType.getBluetoothProfileType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedBluetoothProfileTypes")
    fun testGetBluetoothProfileTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            BluetoothProfileType.getBluetoothProfileType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun bluetoothProfileTypes() =
            BluetoothProfileType
                .values()
                .map { bluetoothProfileType -> Arguments.of(bluetoothProfileType.type, bluetoothProfileType) }
                .toList()

        @JvmStatic
        fun unsupportedBluetoothProfileTypes(): List<Arguments> {
            val result =
                BluetoothProfileType
                    .values()
                    .map { bluetoothProfileType -> Arguments.of(bluetoothProfileType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}