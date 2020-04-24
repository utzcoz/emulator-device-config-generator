package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.StorageUnitType.B
import com.utzcoz.emulator.device.generator.StorageUnitType.GB
import com.utzcoz.emulator.device.generator.StorageUnitType.KB
import com.utzcoz.emulator.device.generator.StorageUnitType.MB
import com.utzcoz.emulator.device.generator.StorageUnitType.TB
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class StorageUnitTypeTest {
    @ParameterizedTest
    @MethodSource("storageUnitTypes")
    fun testGetStorageUnitTypeWithSupportedTypes(inputType: String, storageUnitType: StorageUnitType) {
        assertThat(StorageUnitType.getStorageUnitType(inputType), hamis(storageUnitType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedStorageUnitTypes")
    fun testGetStorageUnitTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support storage unit type $inputType") {
            StorageUnitType.getStorageUnitType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun storageUnitTypes() = listOf(
            Arguments.of(B.type, B),
            Arguments.of(KB.type, KB),
            Arguments.of(MB.type, MB),
            Arguments.of(GB.type, GB),
            Arguments.of(TB.type, TB)
        )

        @JvmStatic
        fun unsupportedStorageUnitTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(B.type + "-unsupported"),
            Arguments.of(KB.type + "-unsupported"),
            Arguments.of(MB.type + "-unsupported"),
            Arguments.of(GB.type + "-unsupported"),
            Arguments.of(TB.type + "-unsupported")
        )
    }
}