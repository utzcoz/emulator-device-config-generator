package com.utzcoz.emulator.device.generator.type

enum class PowerType(val type: String) {
    PLUGGED_IN("plugged-in"),
    BATTERY("battery");

    companion object {
        fun getPowerType(inputType: String): PowerType {
            for (powerType in values()) {
                if (powerType.type == inputType) {
                    return powerType
                }
            }
            throw IllegalArgumentException("Don't support power type $inputType")
        }
    }
}