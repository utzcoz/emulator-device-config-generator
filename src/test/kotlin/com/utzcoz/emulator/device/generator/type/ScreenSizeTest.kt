package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScreenSizeTest {
    @ParameterizedTest
    @MethodSource("screenSizeTypes")
    fun testGetScreenSizeTypeWithSupportedTypes(inputType: String, screenSize: ScreenSize) {
        assertEquals(screenSize, ScreenSize.getScreenSizeType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedScreenSizeTypes")
    fun testGetScreenSizeTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            ScreenSize.getScreenSizeType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun screenSizeTypes(): List<Arguments> =
            ScreenSize.values().map { screenSize -> Arguments.of(screenSize.type, screenSize) }.toList()


        @JvmStatic
        fun unsupportedScreenSizeTypes(): List<Arguments> {
            val result =
                ScreenSize
                    .values()
                    .map { screenSize -> Arguments.of(screenSize.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}