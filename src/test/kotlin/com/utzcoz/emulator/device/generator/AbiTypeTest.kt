package com.utzcoz.emulator.device.generator

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class AbiTypeTest {
    @ParameterizedTest
    @MethodSource("abiTypes")
    fun testGetAbiTypeWithSupportedTypes(inputType: String, abiType: AbiType) {
        assertThat(AbiType.getAbiType(inputType), hamis(abiType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedAbiTypes")
    fun testGetAbiTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support abi type $inputType") {
            AbiType.getAbiType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun abiTypes() = listOf(
            Arguments.of(AbiType.ARMEABI.type, AbiType.ARMEABI),
            Arguments.of(AbiType.ARMEABI_V7A.type, AbiType.ARMEABI_V7A),
            Arguments.of(AbiType.ARM64_V8A.type, AbiType.ARM64_V8A),
            Arguments.of(AbiType.X86.type, AbiType.X86),
            Arguments.of(AbiType.X86_64.type, AbiType.X86_64),
            Arguments.of(AbiType.MIPS.type, AbiType.MIPS),
            Arguments.of(AbiType.MIPS64.type, AbiType.MIPS64)
        )

        @JvmStatic
        fun unsupportedAbiTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(AbiType.ARMEABI.type + "-unsupported"),
            Arguments.of(AbiType.ARMEABI_V7A.type + "-unsupported"),
            Arguments.of(AbiType.ARM64_V8A.type + "-unsupported"),
            Arguments.of(AbiType.X86.type + "-unsupported"),
            Arguments.of(AbiType.X86_64.type + "-unsupported"),
            Arguments.of(AbiType.MIPS.type + "-unsupported"),
            Arguments.of(AbiType.MIPS64.type + "-unsupported")
        )

    }
}