package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StorageUnitTypeTest {
    @ParameterizedTest
    @MethodSource("storageUnitTypes")
    fun testGetStorageUnitTypeWithSupportedTypes(inputType: String, storageUnitType: StorageUnitType) {
        assertEquals(storageUnitType, StorageUnitType.getStorageUnitType(inputType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedStorageUnitTypes")
    fun testGetStorageUnitTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException> {
            StorageUnitType.getStorageUnitType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun storageUnitTypes() =
            StorageUnitType
                .values()
                .map { storageUnitType -> Arguments.of(storageUnitType.type, storageUnitType) }
                .toList()

        @JvmStatic
        fun unsupportedStorageUnitTypes(): List<Arguments> {
            val result =
                StorageUnitType
                    .values()
                    .map { storageUnitType -> Arguments.of(storageUnitType.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}