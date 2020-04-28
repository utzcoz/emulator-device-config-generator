package com.utzcoz.emulator.device.generator.hardware

import com.utzcoz.emulator.device.generator.hardware.screen.Screen
import org.dom4j.Element

class Hardware {
    var screen: Screen = Screen()
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
                "nav" -> navType = NavType.getNavType(element.text)
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