package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.Sensor.ACCELEROMETER
import com.utzcoz.emulator.device.generator.Sensor.BAROMETER
import com.utzcoz.emulator.device.generator.Sensor.COMPASS
import com.utzcoz.emulator.device.generator.Sensor.GPS
import com.utzcoz.emulator.device.generator.Sensor.GYROSCOPE
import com.utzcoz.emulator.device.generator.Sensor.LIGHT_SENSOR
import com.utzcoz.emulator.device.generator.Sensor.PROXIMITY_SENSOR
import com.utzcoz.emulator.device.generator.Sensor.STEP_COUNTER
import com.utzcoz.emulator.device.generator.Sensor.STEP_DETECTOR
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class SensorTest {
    @ParameterizedTest
    @MethodSource("sensorTypes")
    fun testGetSensorTypeWithSupportedType(type: String, sensor: Sensor) {
        assertThat(Sensor.getSensorType(type), hamis(sensor))
    }

    @ParameterizedTest
    @MethodSource("unsupportedSensorTypes")
    fun testGetSensorTypeWithUnsupportedType(type: String) {
        assertThrows<IllegalArgumentException>("Don't support sensor $type") {
            Sensor.getSensorType(type)
        }
    }

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

        @JvmStatic
        fun unsupportedSensorTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(ACCELEROMETER.type + "unsupported"),
            Arguments.of(BAROMETER.type + "unsupported"),
            Arguments.of(COMPASS.type + "unsupported"),
            Arguments.of(GPS.type + "unsupported"),
            Arguments.of(GYROSCOPE.type + "unsupported"),
            Arguments.of(LIGHT_SENSOR.type + "unsupported"),
            Arguments.of(PROXIMITY_SENSOR.type + "unsupported"),
            Arguments.of(STEP_COUNTER.type + "unsupported"),
            Arguments.of(STEP_DETECTOR.type + "unsupported")
        )
    }
}