package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.CameraLocation.BACK
import com.utzcoz.emulator.device.generator.CameraLocation.FRONT
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import org.hamcrest.CoreMatchers.`is` as hamis

class CameraLocationTest {
    @ParameterizedTest
    @MethodSource("cameraLocations")
    fun testGetCameraLocationWithSupportedCameraLocations(inputLocation: String, location: CameraLocation) {
        assertThat(CameraLocation.getCameraLocation(inputLocation), hamis(location))
    }

    @ParameterizedTest
    @MethodSource("unsupportedCameraLocations")
    fun testGetCameraLocationWithUnsupportedCameraLocations(inputLocation: String) {
        assertThrows<IllegalArgumentException>("Don't support camera location $inputLocation") {
            CameraLocation.getCameraLocation(inputLocation)
        }
    }

    companion object {
        @JvmStatic
        fun cameraLocations() = listOf(
            Arguments.of(FRONT.location, FRONT),
            Arguments.of(BACK.location, BACK)
        )

        @JvmStatic
        fun unsupportedCameraLocations() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(FRONT.location + "-unsupported"),
            Arguments.of(BACK.location + "-unsupported")
        )
    }
}