package com.utzcoz.emulator.device.generator.type

enum class KeyboardState(val state: String) {
    KEYS_SOFT("keyssoft"),
    KEYS_HIDDEN("keyshidden"),
    KEYS_EXPOSED("keysexposed");

    companion object {
        fun getKeyboardState(inputState: String): KeyboardState {
            for (keyboardState in values()) {
                if (keyboardState.state == inputState) {
                    return keyboardState
                }
            }
            throw IllegalArgumentException("Don't support keyboard state $inputState")
        }
    }
}