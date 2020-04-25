package com.utzcoz.emulator.device.generator.hardware.screen

enum class ScreenType(val type: String) {
    NO_TOUCH("notouch"),
    CAPACITIVE("capacitive"),
    RESISTIVE("resistive");

    companion object {
        fun getScreenType(inputType: String): ScreenType {
            for (screenType in values()) {
                if (screenType.type == inputType) {
                    return screenType
                }
            }
            throw IllegalArgumentException("Don't support screen type $inputType")
        }
    }
}