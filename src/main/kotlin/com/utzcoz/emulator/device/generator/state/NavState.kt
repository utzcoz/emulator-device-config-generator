package com.utzcoz.emulator.device.generator.state

enum class NavState(val state: String) {
    NO_NAV("nonav"),
    NAV_HIDDEN("navhidden"),
    NAV_EXPOSED("navexposed");

    companion object {
        fun getNavState(inputState: String): NavState {
            for (navState in values()) {
                if (navState.state == inputState) {
                    return navState
                }
            }
            throw IllegalArgumentException("Don't support nav state $inputState")
        }
    }
}