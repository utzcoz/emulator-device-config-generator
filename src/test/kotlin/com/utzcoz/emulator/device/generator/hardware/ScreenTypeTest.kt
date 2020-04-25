package com.utzcoz.emulator.device.generator.hardware

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScreenTypeTest {
    @ParameterizedTest
    @MethodSource("screenTypes")
    fun testGetScreenTypeWithSupportedTypes(inputType: String, screenType: ScreenType) {
        assertEquals(screenType, ScreenType.getScreenType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedScreenTypes")
    fun testGetScreenTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            ScreenType.getScreenType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun screenTypes() =
            ScreenType.values().map { screenType -> Arguments.of(screenType.type, screenType) }.toList()

        @JvmStatic
        fun unsupportedScreenTypes(): List<Arguments> {
            val result =
                ScreenType
                    .values()
                    .map { screenType -> Arguments.of(screenType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}