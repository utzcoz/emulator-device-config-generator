package com.utzcoz.emulator.device.generator

import org.dom4j.io.SAXReader
import java.io.File
import java.net.URL

class Device {
    var id: String = ""
    var name: String = ""
    var manufacturer: String = ""
    var hardware: Hardware = Hardware()
    var software: Software = Software()
    var states: Set<State> = mutableSetOf()
    var tagId: String = ""

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
                    "software" -> device.software.parse(element)
                    "state" -> {
                        val state = State()
                        state.parse(element)
                        device.states.plus(state)
                    }
                    "tag-id" -> device.tagId = element.textTrim
                }
            }
            return device
        }
    }
}