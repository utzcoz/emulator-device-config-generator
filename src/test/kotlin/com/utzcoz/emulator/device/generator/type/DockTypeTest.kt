package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DockTypeTest {
    @ParameterizedTest
    @MethodSource("dockTypes")
    fun testGetDockTypeWithSupportedTypes(inputType: String, dockType: DockType) {
        assertEquals(dockType, DockType.getDockType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedDockTypes")
    fun testGetDockTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support dock type $inputType") {
            DockType.getDockType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun dockTypes() =
            DockType.values().map { dockType -> Arguments.of(dockType.type, dockType) }.toList()

        @JvmStatic
        fun unsupportedDockTypes(): List<Arguments> {
            val result =
                DockType
                    .values()
                    .map { dockType -> Arguments.of(dockType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}