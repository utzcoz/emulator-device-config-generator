package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.ButtonsType.HARD
import com.utzcoz.emulator.device.generator.ButtonsType.SOFT
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class ButtonsTypeTest {

    @ParameterizedTest
    @MethodSource("buttonsTypes")
    fun testGetButtonsTypeWithSupportedTypes(inputType: String, buttonsType: ButtonsType) {
        assertThat(ButtonsType.getButtonsType(inputType), hamis(buttonsType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedButtonsTypes")
    fun testGetButtonsTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support buttons type $inputType") {
            ButtonsType.getButtonsType(inputType)
        }
    }


    companion object {
        @JvmStatic
        fun buttonsTypes() = listOf(
            Arguments.of(HARD.type, HARD),
            Arguments.of(SOFT.type, SOFT)
        )

        @JvmStatic
        fun unsupportedButtonsTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(HARD.type + "-unsupported"),
            Arguments.of(SOFT.type + "-unsupported")
        )
    }
}