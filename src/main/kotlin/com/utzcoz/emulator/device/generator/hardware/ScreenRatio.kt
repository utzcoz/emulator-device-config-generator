package com.utzcoz.emulator.device.generator.hardware

enum class ScreenRatio(val type: String) {
    NOT_LONG("notlong"),
    LONG("long");

    companion object {
        fun getScreenRatioType(inputType: String): ScreenRatio {
            for (screenRatio in values()) {
                if (screenRatio.type == inputType) {
                    return screenRatio
                }
            }
            throw IllegalArgumentException("Don't support screen ration $inputType")
        }
    }
}