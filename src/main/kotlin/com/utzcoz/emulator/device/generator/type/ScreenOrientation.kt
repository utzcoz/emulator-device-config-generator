package com.utzcoz.emulator.device.generator.type

enum class ScreenOrientation(val type: String) {
    PORTRAIT("port"),
    LANDSCAPE("land"),
    SQUARE("square");

    companion object {
        fun getScreenOrientationType(inputType: String): ScreenOrientation {
            for (screenOrientation in values()) {
                if (screenOrientation.type == inputType) {
                    return screenOrientation
                }
            }
            throw IllegalArgumentException("Don't support screen orientation $inputType")
        }
    }
}