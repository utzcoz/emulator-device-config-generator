package com.utzcoz.emulator.device.generator.hardware.screen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PixelDensityTest {
    @ParameterizedTest
    @MethodSource("pixelDensityTypes")
    fun testGetPixelDensityTypeWithSupportedTypes(inputType: String, pixelDensity: PixelDensity) {
        assertEquals(pixelDensity, PixelDensity.getPixelDensityType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedPixelDensityTypes")
    fun testGetPixelDensityTypesWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            PixelDensity.getPixelDensityType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun pixelDensityTypes() =
            PixelDensity.values().map { pixelDensity -> Arguments.of(pixelDensity.type, pixelDensity) }.toList()

        @JvmStatic
        fun unsupportedPixelDensityTypes(): List<Arguments> {
            val result =
                PixelDensity
                    .values()
                    .map { pixelDensity -> Arguments.of(pixelDensity.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}