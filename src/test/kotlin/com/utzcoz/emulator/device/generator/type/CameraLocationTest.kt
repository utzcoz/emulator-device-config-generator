package com.utzcoz.emulator.device.generator.type

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CameraLocationTest {
    @ParameterizedTest
    @MethodSource("cameraLocations")
    fun testGetCameraLocationWithSupportedCameraLocations(inputLocation: String, location: CameraLocation) {
        assertEquals(location, CameraLocation.getCameraLocation(inputLocation))
    }

    @ParameterizedTest
    @MethodSource("unsupportedCameraLocations")
    fun testGetCameraLocationWithUnsupportedCameraLocations(inputLocation: String) {
        assertThrows<IllegalArgumentException> {
            CameraLocation.getCameraLocation(inputLocation)
        }
    }

    companion object {
        @JvmStatic
        fun cameraLocations() =
            CameraLocation.values().map { location -> Arguments.of(location.location, location) }.toList()

        @JvmStatic
        fun unsupportedCameraLocations(): List<Arguments> {
            val result =
                CameraLocation
                    .values()
                    .map { location -> Arguments.of(location.location + "-unsupported") }
                    .toMutableList()
            result.add(Arguments.of("unsupported"))
            return result
        }
    }
}