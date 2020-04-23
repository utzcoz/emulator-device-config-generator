package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.Sensor.*
import org.junit.jupiter.params.provider.Arguments

class SensorTest {

    companion object {
        @JvmStatic
        fun sensorTypes() = listOf(
            Arguments.of(ACCELEROMETER.type, ACCELEROMETER),
            Arguments.of(BAROMETER.type, BAROMETER),
            Arguments.of(COMPASS.type, COMPASS),
            Arguments.of(GPS.type, GPS),
            Arguments.of(GYROSCOPE.type, GYROSCOPE),
            Arguments.of(LIGHT_SENSOR.type, LIGHT_SENSOR),
            Arguments.of(PROXIMITY_SENSOR.type, PROXIMITY_SENSOR),
            Arguments.of(STEP_COUNTER.type, STEP_COUNTER),
            Arguments.of(STEP_DETECTOR.type, STEP_DETECTOR)
        )
    }
}