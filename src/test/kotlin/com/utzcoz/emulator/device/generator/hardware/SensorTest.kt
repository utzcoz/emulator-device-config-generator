package com.utzcoz.emulator.device.generator.hardware

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class SensorTest {
    @ParameterizedTest
    @MethodSource("sensorTypes")
    fun testGetSensorTypeWithSupportedType(type: String, sensor: Sensor) {
        assertEquals(sensor, Sensor.getSensorType(type))
    }

    @ParameterizedTest
    @MethodSource("unsupportedSensorTypes")
    fun testGetSensorTypeWithUnsupportedType(type: String) {
        assertThrows<IllegalArgumentException> {
            Sensor.getSensorType(type)
        }
    }

    companion object {
        @JvmStatic
        fun sensorTypes() =
            Sensor.values().map { sensor -> Arguments.of(sensor.type, sensor) }.toList()

        @JvmStatic
        fun unsupportedSensorTypes(): List<Arguments> {
            val result =
                Sensor
                    .values()
                    .map { sensor -> Arguments.of(sensor.type + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}