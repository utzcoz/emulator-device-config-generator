package com.utzcoz.emulator.device.generator

enum class NavType(val type: String) {
    DPAD("dpad"),
    TRACKBALL("trackball"),
    WHEEL("wheel"),
    NO_NAV("nonav");

    companion object {
        fun getNavType(inputType: String): NavType {
            when (inputType) {
                DPAD.type -> return DPAD
                TRACKBALL.type -> return TRACKBALL
                WHEEL.type -> return WHEEL
                NO_NAV.type -> return NO_NAV
            }
            throw IllegalArgumentException("Don't support nav type $inputType")
        }
    }
}