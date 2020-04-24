package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.KeyboardType.NO_KEYS
import com.utzcoz.emulator.device.generator.KeyboardType.QWERTY
import com.utzcoz.emulator.device.generator.KeyboardType.TWELVE_KEY
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class KeyboardTypeTest {
    @ParameterizedTest
    @MethodSource("keyboardTypes")
    fun testGetKeyboardTypeWithSupportedTypes(inputType: String, keyboardType: KeyboardType) {
        assertThat(KeyboardType.getKeyboardType(inputType), hamis(keyboardType))
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
        fun keyboardTypes() = listOf(
            Arguments.of(QWERTY.type, QWERTY),
            Arguments.of(TWELVE_KEY.type, TWELVE_KEY),
            Arguments.of(NO_KEYS.type, NO_KEYS)
        )

        @JvmStatic
        fun unsupportedKeyboardTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(QWERTY.type + "-unsupported"),
            Arguments.of(TWELVE_KEY.type + "-unsupported"),
            Arguments.of(NO_KEYS.type + "-unsupported")
        )
    }
}