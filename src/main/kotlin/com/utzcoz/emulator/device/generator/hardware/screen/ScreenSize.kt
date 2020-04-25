package com.utzcoz.emulator.device.generator.hardware.screen

enum class ScreenSize(val type: String) {
    SMALL("small"),
    NORMAL("normal"),
    LARGE("large"),
    XLARGE("xlarge");

    companion object {
        fun getScreenSizeType(inputType: String): ScreenSize {
            for (screenSize in values()) {
                if (screenSize.type == inputType) {
                    return screenSize
                }
            }
            throw IllegalArgumentException("Don't support screen size $inputType")
        }
    }
}