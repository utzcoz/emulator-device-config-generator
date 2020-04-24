package com.utzcoz.emulator.device.generator

enum class DockType(val type: String) {
    DESK("desk"),
    TV("television"),
    CAR("car");

    companion object {
        fun getDockType(inputType: String): DockType {
            when (inputType) {
                DESK.type -> return DESK
                TV.type -> return TV
                CAR.type -> return CAR
            }
            throw IllegalArgumentException("Don't support dock type $inputType")
        }
    }
}