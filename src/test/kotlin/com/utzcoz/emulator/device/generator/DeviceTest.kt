package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.hardware.Dimensions
import com.utzcoz.emulator.device.generator.hardware.Hardware
import com.utzcoz.emulator.device.generator.hardware.PixelDensity
import com.utzcoz.emulator.device.generator.hardware.Screen
import com.utzcoz.emulator.device.generator.hardware.ScreenRatio
import com.utzcoz.emulator.device.generator.hardware.ScreenSize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

class DeviceTest {
    @TempDir
    lateinit var tmpDir: File

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceBasicInfo(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.name, actual.name)
        assertEquals(expected.id, actual.id)
        assertEquals(expected.manufacturer, actual.manufacturer)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceHardwareScreen(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.hardware.screen.screenSize, actual.hardware.screen.screenSize)
        assertEquals(expected.hardware.screen.pixelDensity, actual.hardware.screen.pixelDensity)
        assertEquals(expected.hardware.screen.screenRatio, actual.hardware.screen.screenRatio)
        assertEquals(expected.hardware.screen.dimensions.xDimension, actual.hardware.screen.dimensions.xDimension)
        assertEquals(expected.hardware.screen.dimensions.yDimension, actual.hardware.screen.dimensions.yDimension)
        assertEquals(expected.hardware.screen.xdpi, actual.hardware.screen.xdpi)
        assertEquals(expected.hardware.screen.ydpi, actual.hardware.screen.ydpi)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testSaveDeviceWithExistTemplates(templateName: String, device: Device) {
        device.name = "test-name"
        device.id = "test-id"
        device.manufacturer = "test-manufacturer"
        device.hardware.screen.screenSize = ScreenSize.XLARGE
        device.hardware.screen.pixelDensity = PixelDensity.XXXHDPI
        device.hardware.screen.screenRatio = ScreenRatio.LONG
        device.hardware.screen.dimensions.xDimension = 2000
        device.hardware.screen.dimensions.yDimension = 2000
        device.hardware.screen.xdpi = 500F
        device.hardware.screen.ydpi = 500F
        val file = File(tmpDir, "test.xml")
        Device.saveDevice(device, File(file.toURI()))
        val saved = Device.readDeviceConfig(file.toURI().toURL())
        assertEquals(device.name, saved.name)
        assertEquals(device.id, saved.id)
        assertEquals(device.manufacturer, saved.manufacturer)
        assertEquals(device.hardware.screen.screenSize, saved.hardware.screen.screenSize)
        assertEquals(device.hardware.screen.pixelDensity, saved.hardware.screen.pixelDensity)
        assertEquals(device.hardware.screen.screenRatio, saved.hardware.screen.screenRatio)
        assertEquals(device.hardware.screen.dimensions.xDimension, saved.hardware.screen.dimensions.xDimension)
        assertEquals(device.hardware.screen.dimensions.yDimension, saved.hardware.screen.dimensions.yDimension)
        assertEquals(device.hardware.screen.xdpi, saved.hardware.screen.xdpi)
        assertEquals(device.hardware.screen.ydpi, saved.hardware.screen.ydpi)
    }

    companion object {
        @JvmStatic
        private fun generateAutomotiveDevice(): Device {
            val automotiveDevice = Device()
            automotiveDevice.name = "Automotive Template"
            automotiveDevice.id = "automotive_template"
            automotiveDevice.manufacturer = "Generic"
            automotiveDevice.hardware = Hardware()
            automotiveDevice.hardware.screen = Screen()
            automotiveDevice.hardware.screen.screenSize = ScreenSize.NORMAL
            automotiveDevice.hardware.screen.pixelDensity = PixelDensity.MDPI
            automotiveDevice.hardware.screen.screenRatio = ScreenRatio.NOT_LONG
            automotiveDevice.hardware.screen.dimensions = Dimensions()
            automotiveDevice.hardware.screen.dimensions.xDimension = 1024
            automotiveDevice.hardware.screen.dimensions.yDimension = 768
            automotiveDevice.hardware.screen.xdpi = 152.00F
            automotiveDevice.hardware.screen.ydpi = 152.00F
            return automotiveDevice
        }

        @JvmStatic
        private fun generateTabletDevice(): Device {
            val tabletDevice = Device()
            tabletDevice.name = "Tablet Template"
            tabletDevice.id = "tablet_template"
            tabletDevice.manufacturer = "Generic"
            tabletDevice.hardware.screen.screenSize = ScreenSize.XLARGE
            tabletDevice.hardware.screen.pixelDensity = PixelDensity.HDPI
            tabletDevice.hardware.screen.screenRatio = ScreenRatio.LONG
            tabletDevice.hardware.screen.dimensions.xDimension = 2560
            tabletDevice.hardware.screen.dimensions.yDimension = 1440
            tabletDevice.hardware.screen.xdpi = 240.00F
            tabletDevice.hardware.screen.ydpi = 240.00F
            return tabletDevice
        }

        @JvmStatic
        private fun generateTVDevice(): Device {
            val tvDevice = Device()
            tvDevice.name = "TV Template"
            tvDevice.name = "TV Template"
            tvDevice.id = "tv_template"
            tvDevice.manufacturer = "Generic"
            tvDevice.hardware.screen.screenSize = ScreenSize.XLARGE
            tvDevice.hardware.screen.pixelDensity = PixelDensity.XHDPI
            tvDevice.hardware.screen.screenRatio = ScreenRatio.LONG
            tvDevice.hardware.screen.dimensions.xDimension = 1920
            tvDevice.hardware.screen.dimensions.yDimension = 1080
            tvDevice.hardware.screen.xdpi = 40.05F
            tvDevice.hardware.screen.ydpi = 40.05F
            return tvDevice
        }

        @JvmStatic
        fun templates(): List<Arguments> {
            val automotiveDevice = generateAutomotiveDevice()
            val tabletDevice = generateTabletDevice()
            val tvDevice = generateTVDevice()
            return listOf(
                Arguments.of("automotive-device.xml", automotiveDevice),
                Arguments.of("tablet-device.xml", tabletDevice),
                Arguments.of("tv-device.xml", tvDevice)
            )
        }
    }
}