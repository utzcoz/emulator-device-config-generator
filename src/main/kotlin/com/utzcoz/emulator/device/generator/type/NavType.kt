package com.utzcoz.emulator.device.generator.type

enum class NavType(val type: String) {
    DPAD("dpad"),
    TRACKBALL("trackball"),
    WHEEL("wheel"),
    NO_NAV("nonav");

    companion object {
        fun getNavType(inputType: String): NavType {
            for (navType in values()) {
                if (navType.type == inputType) {
                    return navType
                }
            }
            throw IllegalArgumentException("Don't support nav type $inputType")
        }
    }
}