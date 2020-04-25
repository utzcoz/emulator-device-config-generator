package com.utzcoz.emulator.device.generator.hardware.screen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MultiTouchTypeTest {
    @ParameterizedTest
    @MethodSource("multiTouchTypes")
    fun testGetMultiTouchTypesWithSupportedTypes(inputType: String, multiTouchType: MultiTouchType) {
        assertEquals(multiTouchType, MultiTouchType.getMultiTouchType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedMultiTouchTypes")
    fun testGetMultiTouchTypesWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            MultiTouchType.getMultiTouchType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun multiTouchTypes() =
            MultiTouchType
                .values()
                .map { multiTouchType -> Arguments.of(multiTouchType.type, multiTouchType) }
                .toList()

        @JvmStatic
        fun unsupportedMultiTouchTypes(): List<Arguments> {
            val result =
                MultiTouchType
                    .values()
                    .map { multiTouchType -> Arguments.of(multiTouchType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}