package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class NavTypeTest {
    @ParameterizedTest
    @MethodSource("navTypes")
    fun testGetNavTypeWithSupportedTypes(inputType: String, navType: NavType) {
        assertEquals(navType, NavType.getNavType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedNavTypes")
    fun testGetNavTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support nav type $inputType") {
            NavType.getNavType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun navTypes() =
            NavType.values().map { navType -> Arguments.of(navType.type, navType) }.toList()

        @JvmStatic
        fun unsupportedNavTypes(): List<Arguments> {
            val result =
                NavType
                    .values()
                    .map { navType -> Arguments.of(navType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}