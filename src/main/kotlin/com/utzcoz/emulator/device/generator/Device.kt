package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.type.AbiType
import com.utzcoz.emulator.device.generator.type.ButtonsType
import com.utzcoz.emulator.device.generator.type.CameraLocation
import com.utzcoz.emulator.device.generator.type.KeyboardType
import com.utzcoz.emulator.device.generator.type.MechanismType
import com.utzcoz.emulator.device.generator.type.MultiTouchType
import com.utzcoz.emulator.device.generator.type.NavType
import com.utzcoz.emulator.device.generator.type.Networking
import com.utzcoz.emulator.device.generator.type.PixelDensity
import com.utzcoz.emulator.device.generator.type.PowerType
import com.utzcoz.emulator.device.generator.type.ScreenRatio
import com.utzcoz.emulator.device.generator.type.ScreenSize
import com.utzcoz.emulator.device.generator.type.ScreenType
import com.utzcoz.emulator.device.generator.type.Sensor
import com.utzcoz.emulator.device.generator.type.StorageUnitType
import org.dom4j.Element
import org.dom4j.io.SAXReader
import java.io.File
import java.net.URL

class Device {
    var id: String = ""
    var name: String = ""
    var manufacturer: String = ""
    var hardware: Hardware = Hardware()

    companion object {
        fun readTemplate(templateName: String): Device {
            val templatePath = "${File.separator}templates${File.separator}$templateName"
            val url = Device::class.java.getResource(templatePath)
            return readTemplateInternal(url)
        }

        private fun readTemplateInternal(url: URL): Device {
            val document = SAXReader().read(url)
            val rootElement = document.rootElement
            val deviceElement = rootElement.element("device")
            assert(deviceElement != null)
            val device = Device()
            for (element in deviceElement.elementIterator()) {
                if (element == null) {
                    continue
                }
                when (element.name) {
                    "name" -> device.name = element.text
                    "id" -> device.id = element.text
                    "manufacturer" -> device.manufacturer = element.text
                    "hardware" -> device.hardware.parse(element)
                }
            }
            return device
        }
    }
}

class Hardware {
    var screen: Screen = Screen()
    var networkingList: NetworkingList = NetworkingList("")
    var sensors: Sensors = Sensors("")
    var mic: Boolean = false
    val cameras: Set<Camera> = mutableSetOf()
    var keyboardType: KeyboardType = KeyboardType.NO_KEYS
    var navType: NavType = NavType.NO_NAV
    var ramUnit: StorageUnitType = StorageUnitType.B
    var ramSize: Int = 0
    var buttonsType: ButtonsType = ButtonsType.SOFT
    var internalStorageUnit: StorageUnitType = StorageUnitType.B
    var internalStorageSize: Int = 0
    var removableStorageUnit: StorageUnitType = StorageUnitType.B
    var removableStorageSize: Int = 0
    var cpu: String = "Generic CPU"
    var gpu: String = "Generic GPU"
    var abiList: AbiList = AbiList("")
    var powerType: PowerType = PowerType.PLUGGED_IN

    fun parse(hardwareElement: Element) {
        for (element in hardwareElement.elementIterator()) {
            when (element.name) {
                "screen" -> screen.parse(element)
                "networking" -> networkingList = NetworkingList(element.text)
                "sensors" -> sensors = Sensors(element.text)
                "mic" -> mic = element.text!!.toBoolean()
                "camera" -> {
                    val camera = Camera()
                    camera.parse(element)
                    cameras.plus(camera)
                }
                "keyboard" -> keyboardType = KeyboardType.getKeyboardType(element.text)
                "nav" -> navType = NavType.getNavType(element.text)
                "ram" -> {
                    ramSize = element.textTrim.toInt()
                    ramUnit = StorageUnitType.getStorageUnitType(element.attribute("unit").text)
                }
                "buttons" -> buttonsType = ButtonsType.getButtonsType(element.text)
                "internal-storage" -> {
                    internalStorageSize = element.textTrim.toInt()
                    internalStorageUnit = StorageUnitType.getStorageUnitType(element.attribute("unit").text)
                }
                "removable-storage" -> {
                    val text = element.textTrim
                    if (text.isNotEmpty()) {
                        removableStorageSize = text.toInt()
                    }
                    removableStorageUnit = StorageUnitType.getStorageUnitType(element.attribute("unit").text)
                }
                "cpu" -> cpu = element.textTrim
                "gpu" -> gpu = element.textTrim
                "abi" -> abiList = AbiList(element.text) // Use element.textTrim will cause lines() work incorrectly.
                "power-type" -> powerType = PowerType.getPowerType(element.text)
            }
        }
    }
}

class Screen {
    var screenSize: ScreenSize = ScreenSize.SMALL
    var diagonalLength: Float = -1F
    var pixelDensity: PixelDensity = PixelDensity.MDPI
    var screenRatio: ScreenRatio = ScreenRatio.NOT_LONG
    var dimensions: Dimensions = Dimensions()
    var xdpi: Float = 0F
    var ydpi: Float = 0F
    var touch: Touch = Touch()

    fun parse(screenElement: Element) {
        for (element in screenElement.elementIterator()) {
            when (element.name) {
                "screen-size" -> screenSize = ScreenSize.getScreenSizeType(element.text)
                "diagonal-length" -> diagonalLength = element.textTrim.toFloat()
                "pixel-density" -> pixelDensity = PixelDensity.getPixelDensityType(element.text)
                "screen-ratio" -> screenRatio = ScreenRatio.getScreenRatioType(element.text)
                "dimensions" -> dimensions.parse(element)
                "xdpi" -> xdpi = element.textTrim.toFloat()
                "ydpi" -> ydpi = element.textTrim.toFloat()
                "touch" -> touch.parse(element)
            }
        }
    }
}

class Dimensions {
    var xDimension: Int = 0
    var yDimension: Int = 0

    fun parse(dimensionsElement: Element) {
        for (element in dimensionsElement.elementIterator()) {
            when (element.name) {
                "x-dimension" -> xDimension = element.textTrim.toInt()
                "y-dimension" -> yDimension = element.textTrim.toInt()
            }
        }
    }
}

class Touch {
    var multiTouchType: MultiTouchType = MultiTouchType.NONE
    var mechanismType: MechanismType = MechanismType.NOT_TOUCH
    var screenType: ScreenType = ScreenType.NO_TOUCH

    fun parse(touchElement: Element) {
        for (element in touchElement.elementIterator()) {
            when (element.name) {
                "multitouch" -> multiTouchType = MultiTouchType.getMultiTouchType(element.text)
                "mechanism" -> mechanismType = MechanismType.getMechanismType(element.text)
                "screen-type" -> screenType = ScreenType.getScreenType(element.text)
            }
        }
    }
}

class NetworkingList(networkingListString: String) {
    var networkingList: List<Networking> = generateNetworkingList(networkingListString)

    companion object {
        private fun generateNetworkingList(networkingListString: String): List<Networking> {
            return networkingListString
                .lines()
                .map { s -> s.trim() }
                .filter { s -> s.isNotEmpty() }
                .map { s -> Networking.getNetworkingType(s) }
                .toList()
        }
    }
}

class Sensors(sensorsString: String) {
    var sensors: List<Sensor> = generateSensors(sensorsString)

    companion object {
        private fun generateSensors(sensorsString: String): List<Sensor> {
            return sensorsString
                .lines()
                .map { s -> s.trim() }
                .filter { s -> s.isNotEmpty() }
                .map { s -> Sensor.getSensorType(s) }
                .toList()
        }
    }
}

class AbiList(abiListString: String) {
    var abiList: List<AbiType> = generateAbiList(abiListString)

    companion object {
        private fun generateAbiList(abiListString: String): List<AbiType> {
            return abiListString
                .splitToSequence("\r\n", "\n", "\r", "\t", "\\s+")
                .map { s -> s.trim() }
                .filter { s -> s.isNotEmpty() }
                .map { s -> AbiType.getAbiType(s) }
                .toList()
        }
    }
}

class Camera {
    var location: CameraLocation = CameraLocation.FRONT
    var autoFocus: Boolean = false
    var flash: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (other !is Camera) {
            return false
        }
        return location == other.location && autoFocus == other.autoFocus && flash == other.flash
    }

    override fun hashCode(): Int {
        return ((location.hashCode() * 31) + autoFocus.hashCode()) * 31 + flash.hashCode()
    }

    override fun toString(): String {
        return "Camera [ location: ${location.location}, autoFocus: $autoFocus, flash: $flash ]"
    }

    fun parse(cameraElement: Element) {
        for (element in cameraElement.elementIterator()) {
            when (element.name) {
                "location" -> location = CameraLocation.getCameraLocation(element.text)
                "autofocus" -> autoFocus = element.text!!.toBoolean()
                "flash" -> flash = element.text!!.toBoolean()
            }
        }
    }
}