package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MechanismTypeTest {
    @ParameterizedTest
    @MethodSource("mechanismTypes")
    fun testGetMechanismTypeWithSupportedTypes(inputType: String, mechanismType: MechanismType) {
        assertEquals(mechanismType, MechanismType.getMechanismType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedMechanismTypes")
    fun testGetMechanismTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            MechanismType.getMechanismType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun mechanismTypes() =
            MechanismType.values().map { mechanismType -> Arguments.of(mechanismType.type, mechanismType) }.toList()

        @JvmStatic
        fun unsupportedMechanismTypes(): List<Arguments> {
            val result =
                MechanismType
                    .values()
                    .map { mechanismType -> Arguments.of(mechanismType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}