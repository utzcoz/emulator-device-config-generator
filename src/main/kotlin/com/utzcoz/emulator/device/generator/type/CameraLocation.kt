package com.utzcoz.emulator.device.generator.type

enum class CameraLocation(val location: String) {
    FRONT("front"),
    BACK("back");

    companion object {
        fun getCameraLocation(inputLocation: String): CameraLocation {
            for (location in values()) {
                if (location.location == inputLocation) {
                    return location
                }
            }
            throw IllegalArgumentException("Don't support camera location $inputLocation")
        }
    }
}