package com.utzcoz.emulator.device.generator

enum class PowerType(val type: String) {
    PLUGGED_IN("plugged-in"),
    BATTERY("battery");

    companion object {
        fun getPowerType(inputType: String): PowerType {
            when (inputType) {
                PLUGGED_IN.type -> return PLUGGED_IN
                BATTERY.type -> return BATTERY
            }
            throw IllegalArgumentException("Don't support power type $inputType")
        }
    }
}