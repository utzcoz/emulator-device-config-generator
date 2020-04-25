package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScreenOrientationTest {
    @ParameterizedTest
    @MethodSource("screenOrientationTypes")
    fun testGetScreenOrientationWithSupportedTypes(inputType: String, screenOrientation: ScreenOrientation) {
        assertEquals(screenOrientation, ScreenOrientation.getScreenOrientationType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedScreenOrientationTypes")
    fun testGetScreenOrientationWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            ScreenOrientation.getScreenOrientationType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun screenOrientationTypes() =
            ScreenOrientation
                .values()
                .map { orientation -> Arguments.of(orientation.type, orientation) }
                .toList()

        @JvmStatic
        fun unsupportedScreenOrientationTypes(): List<Arguments> {
            val result =
                ScreenOrientation
                    .values()
                    .map { orientation -> Arguments.of(orientation.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}