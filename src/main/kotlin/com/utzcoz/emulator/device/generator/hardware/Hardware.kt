package com.utzcoz.emulator.device.generator.hardware

import com.utzcoz.emulator.device.generator.hardware.screen.Screen
import org.dom4j.Element

class Hardware {
    var screen: Screen =
        Screen()
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

    fun parse(hardwareElement: Element) {
        for (element in hardwareElement.elementIterator()) {
            when (element.name) {
                "screen" -> screen.parse(element)
                "mic" -> mic = element.text!!.toBoolean()
                "camera" -> {
                    val camera = Camera()
                    camera.parse(element)
                    cameras.plus(camera)
                }
                "keyboard" -> keyboardType =
                    KeyboardType.getKeyboardType(
                        element.text
                    )
                "nav" -> navType =
                    NavType.getNavType(element.text)
                "ram" -> {
                    ramSize = element.textTrim.toInt()
                    ramUnit =
                        StorageUnitType.getStorageUnitType(
                            element.attribute("unit").text
                        )
                }
                "buttons" -> buttonsType =
                    ButtonsType.getButtonsType(
                        element.text
                    )
                "internal-storage" -> {
                    internalStorageSize = element.textTrim.toInt()
                    internalStorageUnit =
                        StorageUnitType.getStorageUnitType(
                            element.attribute("unit").text
                        )
                }
                "removable-storage" -> {
                    val text = element.textTrim
                    if (text.isNotEmpty()) {
                        removableStorageSize = text.toInt()
                    }
                    removableStorageUnit =
                        StorageUnitType.getStorageUnitType(
                            element.attribute("unit").text
                        )
                }
            }
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
                "location" -> location =
                    CameraLocation.getCameraLocation(
                        element.text
                    )
                "autofocus" -> autoFocus = element.text!!.toBoolean()
                "flash" -> flash = element.text!!.toBoolean()
            }
        }
    }
}