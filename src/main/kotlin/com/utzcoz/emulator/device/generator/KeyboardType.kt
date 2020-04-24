package com.utzcoz.emulator.device.generator

enum class KeyboardType(val type: String) {
    QWERTY("qwerty"),
    TWELVE_KEY("12key"),
    NO_KEYS("nokeys");

    companion object {
        fun getKeyboardType(inputType: String): KeyboardType {
            when (inputType) {
                QWERTY.type -> return QWERTY
                TWELVE_KEY.type -> return TWELVE_KEY
                NO_KEYS.type -> return NO_KEYS
            }
            throw IllegalArgumentException("Don't support keyboard type $inputType")
        }
    }
}