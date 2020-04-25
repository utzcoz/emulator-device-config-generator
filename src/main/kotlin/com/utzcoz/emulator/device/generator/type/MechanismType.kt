package com.utzcoz.emulator.device.generator.type

enum class MechanismType(val type: String) {
    NOT_TOUCH("notouch"),
    STYLUS("stylus"),
    FINGER("finger");

    companion object {
        fun getMechanismType(inputType: String): MechanismType {
            for (mechanismType in values()) {
                if (mechanismType.type == inputType) {
                    return mechanismType
                }
            }
            throw IllegalArgumentException("Don't support mechanism type $inputType")
        }
    }
}