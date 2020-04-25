package com.utzcoz.emulator.device.generator.state

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class KeyboardStateTest {
    @ParameterizedTest
    @MethodSource("keyboardStates")
    fun testGetKeyboardStateWithSupportedStates(inputState: String, keyboardState: KeyboardState) {
        assertEquals(keyboardState,
            KeyboardState.getKeyboardState(
                inputState
            )
        )
    }

    @ParameterizedTest
    @MethodSource("unsupportedKeyboardStates")
    fun testGetKeyboardStateWithUnsupportedStates(inputState: String) {
        assertThrows<IllegalArgumentException> {
            KeyboardState.getKeyboardState(
                inputState
            )
        }
    }

    companion object {
        @JvmStatic
        fun keyboardStates() =
            KeyboardState
                .values()
                .map { keyboardState -> Arguments.of(keyboardState.state, keyboardState) }
                .toList()

        @JvmStatic
        fun unsupportedKeyboardStates(): List<Arguments> {
            val result =
                KeyboardState
                    .values()
                    .map { keyboardState -> Arguments.of(keyboardState.state + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}