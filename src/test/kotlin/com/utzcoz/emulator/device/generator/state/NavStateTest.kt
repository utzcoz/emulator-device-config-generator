package com.utzcoz.emulator.device.generator.state

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class NavStateTest {
    @ParameterizedTest
    @MethodSource("navStates")
    fun testGetNavStateWithSupportedStates(inputState: String, navState: NavState) {
        assertEquals(navState,
            NavState.getNavState(inputState)
        )
    }

    @ParameterizedTest
    @MethodSource("unsupportedNavStates")
    fun testGetNavStateWithUnsupportedStates(inputState: String) {
        assertThrows<IllegalArgumentException> {
            NavState.getNavState(inputState)
        }
    }


    companion object {
        @JvmStatic
        fun navStates() =
            NavState.values().map { navState -> Arguments.of(navState.state, navState) }.toList()

        @JvmStatic
        fun unsupportedNavStates(): List<Arguments> {
            val result =
                NavState
                    .values()
                    .map { navState -> Arguments.of(navState.state + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}