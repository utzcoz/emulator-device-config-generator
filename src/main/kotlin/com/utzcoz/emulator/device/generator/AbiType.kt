package com.utzcoz.emulator.device.generator

enum class AbiType(val type: String) {
    ARMEABI("armeabi"),
    ARMEABI_V7A("armeabi-v7a"),
    ARM64_V8A("arm64-v8a"),
    X86("x86"),
    X86_64("x86_64"),
    MIPS("mips"),
    MIPS64("mips64");

    companion object {
        fun getAbiType(inputType: String): AbiType {
            when (inputType) {
                ARMEABI.type -> return ARMEABI
                ARMEABI_V7A.type -> return ARMEABI_V7A
                ARM64_V8A.type -> return ARM64_V8A
                X86.type -> return X86
                X86_64.type -> return X86_64
                MIPS.type -> return MIPS
                MIPS64.type -> return MIPS64
            }
            throw IllegalArgumentException("Don't support abi type $inputType")
        }
    }
}