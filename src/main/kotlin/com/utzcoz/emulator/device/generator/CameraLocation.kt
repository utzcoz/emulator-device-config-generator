package com.utzcoz.emulator.device.generator

enum class CameraLocation(val location: String) {
    FRONT("front"),
    BACK("back");

    companion object {
        fun getCameraLocation(inputLocation: String): CameraLocation {
            when (inputLocation) {
                FRONT.location -> return FRONT
                BACK.location -> return BACK
            }
            throw IllegalArgumentException("Don't support camera location $inputLocation")
        }
    }
}