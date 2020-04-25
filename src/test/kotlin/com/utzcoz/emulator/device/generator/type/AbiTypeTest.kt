package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class AbiTypeTest {
    @ParameterizedTest
    @MethodSource("abiTypes")
    fun testGetAbiTypeWithSupportedTypes(inputType: String, abiType: AbiType) {
        assertEquals(abiType, AbiType.getAbiType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedAbiTypes")
    fun testGetAbiTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            AbiType.getAbiType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun abiTypes() =
            AbiType.values().map { abiType -> Arguments.of(abiType.type, abiType) }.toList()

        @JvmStatic
        fun unsupportedAbiTypes(): List<Arguments> {
            val result =
                AbiType
                    .values()
                    .map { abiType -> Arguments.of(abiType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}