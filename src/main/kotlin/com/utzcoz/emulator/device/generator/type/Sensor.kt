package com.utzcoz.emulator.device.generator.type

import java.lang.IllegalArgumentException

enum class Sensor(val type: String) {
    ACCELEROMETER("Accelerometer"),
    BAROMETER("Barometer"),
    COMPASS("Compass"),
    GPS("GPS"),
    GYROSCOPE("Gyroscope"),
    LIGHT_SENSOR("LightSensor"),
    PROXIMITY_SENSOR("ProximitySensor"),
    STEP_COUNTER("StepCounter"),
    STEP_DETECTOR("StepDetector");

    companion object {
        fun getSensorType(inputType: String): Sensor {
            for (sensor in values()) {
                if (sensor.type == inputType) {
                    return sensor
                }
            }
            throw IllegalArgumentException("Don't support sensor $inputType")
        }
    }
}