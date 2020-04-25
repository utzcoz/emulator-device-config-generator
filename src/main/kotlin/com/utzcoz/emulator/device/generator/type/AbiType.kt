package com.utzcoz.emulator.device.generator.type

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
            for (abiType in values()) {
                if (abiType.type == inputType) {
                    return abiType
                }
            }
            throw IllegalArgumentException("Don't support abi type $inputType")
        }
    }
}