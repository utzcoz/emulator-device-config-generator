package com.utzcoz.emulator.device.generator.hardware

enum class MultiTouchType(val type: String) {
    NONE("none"),
    BASIC("basic"),
    DISTINCT("distinct"),
    JAZZ_HANDS("jazz-hands");

    companion object {
        fun getMultiTouchType(inputType: String): MultiTouchType {
            for (multiTouchType in values()) {
                if (multiTouchType.type == inputType) {
                    return multiTouchType
                }
            }
            throw IllegalArgumentException("Don't support multitouch type $inputType")
        }

    }
}