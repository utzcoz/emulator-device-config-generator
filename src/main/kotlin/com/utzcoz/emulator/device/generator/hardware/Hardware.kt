package com.utzcoz.emulator.device.generator.hardware

import com.utzcoz.emulator.device.generator.hardware.screen.Screen
import org.dom4j.Element

class Hardware {
    var screen: Screen = Screen()
    var navType: NavType = NavType.NO_NAV
    var buttonsType: ButtonsType = ButtonsType.SOFT

    fun parse(hardwareElement: Element) {
        for (element in hardwareElement.elementIterator()) {
            when (element.name) {
                "screen" -> screen.parse(element)
                "nav" -> navType = NavType.getNavType(element.text)
                "buttons" -> buttonsType =
                    ButtonsType.getButtonsType(
                        element.text
                    )
            }
        }
    }
}