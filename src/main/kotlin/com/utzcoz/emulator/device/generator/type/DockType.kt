package com.utzcoz.emulator.device.generator.type

enum class DockType(val type: String) {
    DESK("desk"),
    TV("television"),
    CAR("car");

    companion object {
        fun getDockType(inputType: String): DockType {
            for (dockType in values()) {
                if (dockType.type == inputType) {
                    return dockType
                }
            }
            throw IllegalArgumentException("Don't support dock type $inputType")
        }
    }
}