package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.PowerType.BATTERY
import com.utzcoz.emulator.device.generator.PowerType.PLUGGED_IN
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class PowerTypeTest {
    @ParameterizedTest
    @MethodSource("powerTypes")
    fun testGetPowerTypeWithSupportedTypes(inputType: String, powerType: PowerType) {
        assertThat(PowerType.getPowerType(inputType), hamis(powerType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedPowerTypes")
    fun testGetPowerTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support power type $inputType") {
            PowerType.getPowerType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun powerTypes() = listOf(
            Arguments.of(PLUGGED_IN.type, PLUGGED_IN),
            Arguments.of(BATTERY.type, BATTERY)
        )

        @JvmStatic
        fun unsupportedPowerTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(PLUGGED_IN.type + "-unsupported"),
            Arguments.of(BATTERY.type + "-unsupported")
        )
    }
}