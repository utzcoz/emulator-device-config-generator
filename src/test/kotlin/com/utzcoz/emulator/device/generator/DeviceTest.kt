package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.type.ButtonsType
import com.utzcoz.emulator.device.generator.type.CameraLocation
import com.utzcoz.emulator.device.generator.type.KeyboardType
import com.utzcoz.emulator.device.generator.type.MechanismType
import com.utzcoz.emulator.device.generator.type.MultiTouchType
import com.utzcoz.emulator.device.generator.type.NavType
import com.utzcoz.emulator.device.generator.type.PixelDensity
import com.utzcoz.emulator.device.generator.type.PowerType
import com.utzcoz.emulator.device.generator.type.ScreenRatio
import com.utzcoz.emulator.device.generator.type.ScreenSize
import com.utzcoz.emulator.device.generator.type.ScreenType
import com.utzcoz.emulator.device.generator.type.StorageUnitType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DeviceTest {
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
        assertEquals(expected.hardware.screen.diagonalLength, actual.hardware.screen.diagonalLength)
        assertEquals(expected.hardware.screen.pixelDensity, actual.hardware.screen.pixelDensity)
        assertEquals(expected.hardware.screen.screenRatio, actual.hardware.screen.screenRatio)
        assertEquals(expected.hardware.screen.dimensions.xDimension, actual.hardware.screen.dimensions.xDimension)
        assertEquals(expected.hardware.screen.dimensions.yDimension, actual.hardware.screen.dimensions.yDimension)
        assertEquals(expected.hardware.screen.xdpi, actual.hardware.screen.xdpi)
        assertEquals(expected.hardware.screen.ydpi, actual.hardware.screen.ydpi)
        assertEquals(expected.hardware.screen.touch.multiTouchType, actual.hardware.screen.touch.multiTouchType)
        assertEquals(expected.hardware.screen.touch.mechanismType, actual.hardware.screen.touch.mechanismType)
        assertEquals(expected.hardware.screen.touch.screenType, actual.hardware.screen.touch.screenType)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceHardware(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.hardware.networkingList.networkingList, actual.hardware.networkingList.networkingList)
        assertEquals(expected.hardware.sensors.sensors, actual.hardware.sensors.sensors)
        assertEquals(expected.hardware.mic, actual.hardware.mic)
        assertTrue(actual.hardware.mic)
        assertEquals(expected.hardware.cameras, actual.hardware.cameras)
        assertEquals(expected.hardware.keyboardType, actual.hardware.keyboardType)
        assertEquals(expected.hardware.navType, actual.hardware.navType)
        assertEquals(expected.hardware.buttonsType, actual.hardware.buttonsType)
        assertEquals(expected.hardware.cpu, actual.hardware.cpu)
        assertEquals(expected.hardware.gpu, actual.hardware.gpu)
        assertEquals(expected.hardware.abiList.abiList, actual.hardware.abiList.abiList)
        assertEquals(expected.hardware.powerType, actual.hardware.powerType)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceHardwareStorage(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.hardware.ramSize, actual.hardware.ramSize)
        assertEquals(expected.hardware.ramUnit, actual.hardware.ramUnit)
        assertEquals(expected.hardware.internalStorageSize, actual.hardware.internalStorageSize)
        assertEquals(expected.hardware.internalStorageUnit, actual.hardware.internalStorageUnit)
        assertEquals(expected.hardware.removableStorageSize, actual.hardware.removableStorageSize)
        assertEquals(expected.hardware.removableStorageUnit, actual.hardware.removableStorageUnit)
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
            automotiveDevice.hardware.screen.diagonalLength = 8.4F
            automotiveDevice.hardware.screen.pixelDensity = PixelDensity.MDPI
            automotiveDevice.hardware.screen.screenRatio = ScreenRatio.NOT_LONG
            automotiveDevice.hardware.screen.dimensions = Dimensions()
            automotiveDevice.hardware.screen.dimensions.xDimension = 1024
            automotiveDevice.hardware.screen.dimensions.yDimension = 768
            automotiveDevice.hardware.screen.xdpi = 152.00F
            automotiveDevice.hardware.screen.ydpi = 152.00F
            automotiveDevice.hardware.screen.touch = Touch()
            automotiveDevice.hardware.screen.touch.multiTouchType = MultiTouchType.BASIC
            automotiveDevice.hardware.screen.touch.mechanismType = MechanismType.FINGER
            automotiveDevice.hardware.screen.touch.screenType = ScreenType.CAPACITIVE
            val networkingListString = """
                Bluetooth
                Wifi
                NFC
            """.trimIndent()
            automotiveDevice.hardware.networkingList = NetworkingList(networkingListString)
            val sensorsString = """
                GPS
                LightSensor
            """.trimIndent()
            automotiveDevice.hardware.sensors = Sensors(sensorsString)
            automotiveDevice.hardware.mic = true
            automotiveDevice.hardware.keyboardType = KeyboardType.NO_KEYS
            automotiveDevice.hardware.ramUnit = StorageUnitType.KB
            automotiveDevice.hardware.ramSize = 3774492
            automotiveDevice.hardware.internalStorageUnit = StorageUnitType.KB
            automotiveDevice.hardware.internalStorageSize = 10255672
            automotiveDevice.hardware.removableStorageUnit = StorageUnitType.TB
            val abiListString = """
                armeabi-v7a
                x86
            """.trimIndent()
            automotiveDevice.hardware.abiList = AbiList(abiListString)
            return automotiveDevice
        }

        @JvmStatic
        private fun generateTabletDevice(): Device {
            val tabletDevice = Device()
            tabletDevice.name = "Tablet Template"
            tabletDevice.id = "tablet_template"
            tabletDevice.manufacturer = "Generic"
            tabletDevice.hardware = Hardware()
            tabletDevice.hardware.screen = Screen()
            tabletDevice.hardware.screen.screenSize = ScreenSize.XLARGE
            tabletDevice.hardware.screen.diagonalLength = 13.50F
            tabletDevice.hardware.screen.pixelDensity = PixelDensity.HDPI
            tabletDevice.hardware.screen.screenRatio = ScreenRatio.LONG
            tabletDevice.hardware.screen.dimensions = Dimensions()
            tabletDevice.hardware.screen.dimensions.xDimension = 2560
            tabletDevice.hardware.screen.dimensions.yDimension = 1440
            tabletDevice.hardware.screen.xdpi = 240.00F
            tabletDevice.hardware.screen.ydpi = 240.00F
            tabletDevice.hardware.screen.touch = Touch()
            tabletDevice.hardware.screen.touch.multiTouchType = MultiTouchType.JAZZ_HANDS
            tabletDevice.hardware.screen.touch.mechanismType = MechanismType.FINGER
            tabletDevice.hardware.screen.touch.screenType = ScreenType.CAPACITIVE
            val networkingListString = """
                Bluetooth
                Wifi
                NFC
            """.trimIndent()
            tabletDevice.hardware.networkingList = NetworkingList(networkingListString)
            val sensorsString = """
                Accelerometer
                Barometer
                Compass
                GPS
                Gyroscope
                LightSensor
                ProximitySensor
            """.trimIndent()
            tabletDevice.hardware.sensors = Sensors(sensorsString)
            tabletDevice.hardware.mic = true
            val cameraFront = Camera()
            cameraFront.location = CameraLocation.FRONT
            cameraFront.autoFocus = true
            cameraFront.flash = false
            val cameraBack = Camera()
            cameraBack.location = CameraLocation.BACK
            cameraBack.autoFocus = true
            cameraBack.flash = true
            tabletDevice.hardware.cameras.plus(cameraFront).plus(cameraBack)
            tabletDevice.hardware.keyboardType = KeyboardType.NO_KEYS
            tabletDevice.hardware.ramUnit = StorageUnitType.GB
            tabletDevice.hardware.ramSize = 4
            tabletDevice.hardware.internalStorageSize = 64
            tabletDevice.hardware.internalStorageUnit = StorageUnitType.GB
            tabletDevice.hardware.removableStorageUnit = StorageUnitType.TB
            tabletDevice.hardware.cpu = "Qualcomm Snapdragon 845"
            tabletDevice.hardware.gpu = "Adreno 630"
            val abiListString = """
                armeabi
                armeabi-v7a
                arm64-v8a
            """.trimIndent()
            tabletDevice.hardware.abiList = AbiList(abiListString)
            tabletDevice.hardware.powerType = PowerType.BATTERY
            return tabletDevice
        }

        @JvmStatic
        private fun generateTVDevice(): Device {
            val tvDevice = Device()
            tvDevice.name = "TV Template"
            tvDevice.name = "TV Template"
            tvDevice.id = "tv_template"
            tvDevice.manufacturer = "Generic"
            tvDevice.hardware = Hardware()
            tvDevice.hardware.screen = Screen()
            tvDevice.hardware.screen.screenSize = ScreenSize.XLARGE
            tvDevice.hardware.screen.diagonalLength = 55.00F
            tvDevice.hardware.screen.pixelDensity = PixelDensity.XHDPI
            tvDevice.hardware.screen.screenRatio = ScreenRatio.LONG
            tvDevice.hardware.screen.dimensions = Dimensions()
            tvDevice.hardware.screen.dimensions.xDimension = 1920
            tvDevice.hardware.screen.dimensions.yDimension = 1080
            tvDevice.hardware.screen.xdpi = 40.05F
            tvDevice.hardware.screen.ydpi = 40.05F
            tvDevice.hardware.screen.touch = Touch()
            tvDevice.hardware.screen.touch.multiTouchType = MultiTouchType.NONE
            tvDevice.hardware.screen.touch.mechanismType = MechanismType.NOT_TOUCH
            tvDevice.hardware.screen.touch.screenType = ScreenType.NO_TOUCH
            val networkingListString = """
                Bluetooth
                Wifi
                NFC
            """.trimIndent()
            tvDevice.hardware.networkingList = NetworkingList(networkingListString)
            val sensorsString = """
                GPS
                LightSensor
            """.trimIndent()
            tvDevice.hardware.sensors = Sensors(sensorsString)
            tvDevice.hardware.mic = true
            tvDevice.hardware.keyboardType = KeyboardType.QWERTY
            tvDevice.hardware.navType = NavType.DPAD
            tvDevice.hardware.ramUnit = StorageUnitType.GB
            tvDevice.hardware.ramSize = 2
            tvDevice.hardware.buttonsType = ButtonsType.HARD
            tvDevice.hardware.internalStorageUnit = StorageUnitType.KB
            tvDevice.hardware.internalStorageSize = 7811891
            tvDevice.hardware.removableStorageUnit = StorageUnitType.TB
            val abiListString = """
                armeabi-v7a
                x86
            """.trimIndent()
            tvDevice.hardware.abiList = AbiList(abiListString)
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