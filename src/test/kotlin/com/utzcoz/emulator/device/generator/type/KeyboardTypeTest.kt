package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class KeyboardTypeTest {
    @ParameterizedTest
    @MethodSource("keyboardTypes")
    fun testGetKeyboardTypeWithSupportedTypes(inputType: String, keyboardType: KeyboardType) {
        assertEquals(keyboardType, KeyboardType.getKeyboardType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedKeyboardTypes")
    fun testGetKeyboardTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support keyboard type $inputType") {
            KeyboardType.getKeyboardType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun keyboardTypes() =
            KeyboardType.values().map { keyboardType -> Arguments.of(keyboardType.type, keyboardType) }.toList()

        @JvmStatic
        fun unsupportedKeyboardTypes(): List<Arguments> {
            val result =
                KeyboardType
                    .values()
                    .map { keyboardType -> Arguments.of(keyboardType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}