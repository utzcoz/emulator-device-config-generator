package com.utzcoz.emulator.device.generator.hardware

import org.dom4j.Element

class Hardware {
    var screen: Screen =
        Screen()

    fun parse(hardwareElement: Element) {
        for (element in hardwareElement.elementIterator()) {
            when (element.name) {
                "screen" -> screen.parse(element)
            }
        }
    }
}