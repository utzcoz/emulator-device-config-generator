package com.utzcoz.emulator.device.generator

import java.lang.IllegalArgumentException

/**
 * The sensor type based on com.android.dvlib.devices-5.xsd.
 */
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
            when (inputType) {
                ACCELEROMETER.type -> return ACCELEROMETER
                BAROMETER.type -> return BAROMETER
                COMPASS.type -> return COMPASS
                GPS.type -> return GPS
                GYROSCOPE.type -> return GYROSCOPE
                LIGHT_SENSOR.type -> return LIGHT_SENSOR
                PROXIMITY_SENSOR.type -> return PROXIMITY_SENSOR
                STEP_COUNTER.type -> return STEP_COUNTER
                STEP_DETECTOR.type -> return STEP_DETECTOR
            }
            throw IllegalArgumentException("Don't support sensor $inputType")
        }
    }
}