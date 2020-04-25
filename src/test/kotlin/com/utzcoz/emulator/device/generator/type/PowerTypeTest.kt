package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PowerTypeTest {
    @ParameterizedTest
    @MethodSource("powerTypes")
    fun testGetPowerTypeWithSupportedTypes(inputType: String, powerType: PowerType) {
        assertEquals(powerType, PowerType.getPowerType(inputType))
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
        fun powerTypes() =
            PowerType.values().map { powerType -> Arguments.of(powerType.type, powerType) }.toList()

        @JvmStatic
        fun unsupportedPowerTypes(): List<Arguments> {
            val result =
                PowerType
                    .values()
                    .map { powerType -> Arguments.of(powerType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}