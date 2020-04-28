package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.hardware.Hardware
import org.dom4j.io.OutputFormat
import org.dom4j.io.SAXReader
import org.dom4j.io.XMLWriter
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class Device {
    private var templateName: String = ""
    var id: String = ""
    var name: String = ""
    var manufacturer: String = ""
    var hardware: Hardware = Hardware()

    companion object {
        fun readTemplate(templateName: String): Device {
            val templatePath = "/templates/$templateName"
            val url = Device::class.java.getResource(templatePath)
            return readTemplateInternal(templateName, url)
        }

        fun readDeviceConfig(url: URL): Device = readTemplateInternal("", url)

        private fun readTemplateInternal(templateName: String, url: URL): Device {
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
            device.templateName = templateName
            return device
        }

        fun saveDevice(device: Device, savedFile: File) {
            val templateName =
                if (device.templateName.isEmpty()) "tablet-device.xml" else device.templateName
            val document = SAXReader().read(Device::class.java.getResource("/templates/$templateName"))
            val rootElement = document.rootElement.element("device")
            for (element in rootElement.elementIterator()) {
                if (element == null) {
                    continue
                }
                when (element.name) {
                    "name" -> element.text = device.name
                    "id" -> element.text = device.id
                    "manufacturer" -> element.text = device.manufacturer
                    "hardware" -> device.hardware.save(element)
                }
            }
            val fos = FileOutputStream(savedFile)
            val format = OutputFormat.createPrettyPrint()
            val writer = XMLWriter(fos, format)
            writer.write(document)
            writer.flush()
        }
    }
}