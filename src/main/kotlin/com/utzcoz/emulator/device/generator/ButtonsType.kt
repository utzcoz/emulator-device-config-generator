package com.utzcoz.emulator.device.generator

enum class ButtonsType(val type: String) {
    HARD("hard"),
    SOFT("soft");

    companion object {
        fun getButtonsType(inputType: String): ButtonsType {
            when (inputType) {
                HARD.type -> return HARD
                SOFT.type -> return SOFT
            }
            throw IllegalArgumentException("Don't support buttons type $inputType")
        }
    }
}