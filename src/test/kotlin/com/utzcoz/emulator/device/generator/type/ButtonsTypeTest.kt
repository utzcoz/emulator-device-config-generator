package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ButtonsTypeTest {

    @ParameterizedTest
    @MethodSource("buttonsTypes")
    fun testGetButtonsTypeWithSupportedTypes(inputType: String, buttonsType: ButtonsType) {
        assertEquals(buttonsType, ButtonsType.getButtonsType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedButtonsTypes")
    fun testGetButtonsTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            ButtonsType.getButtonsType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun buttonsTypes() =
            ButtonsType.values().map { buttonsType -> Arguments.of(buttonsType.type, buttonsType) }.toList()

        @JvmStatic
        fun unsupportedButtonsTypes(): List<Arguments> {
            val result =
                ButtonsType
                    .values()
                    .map { buttonsType -> Arguments.of(buttonsType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}