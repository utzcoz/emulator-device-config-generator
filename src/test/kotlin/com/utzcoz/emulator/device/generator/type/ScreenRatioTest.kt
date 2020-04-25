package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScreenRatioTest {
    @ParameterizedTest
    @MethodSource("screenRatioTypes")
    fun testGetScreenRatioWithSupportedTypes(inputType: String, screenRatio: ScreenRatio) {
        assertEquals(screenRatio, ScreenRatio.getScreenRatioType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedScreenRatioTypes")
    fun testGetScreenRatioWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            ScreenRatio.getScreenRatioType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun screenRatioTypes() =
            ScreenRatio.values().map { screenRatio -> Arguments.of(screenRatio.type, screenRatio) }.toList()

        @JvmStatic
        fun unsupportedScreenRatioTypes(): List<Arguments> {
            val result =
                ScreenRatio
                    .values()
                    .map { screenRatio -> Arguments.of(screenRatio.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}