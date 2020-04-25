package com.utzcoz.emulator.device.generator.type

enum class ButtonsType(val type: String) {
    HARD("hard"),
    SOFT("soft");

    companion object {
        fun getButtonsType(inputType: String): ButtonsType {
            for (buttonsType in values()) {
                if (buttonsType.type == inputType) {
                    return buttonsType
                }
            }
            throw IllegalArgumentException("Don't support buttons type $inputType")
        }
    }
}