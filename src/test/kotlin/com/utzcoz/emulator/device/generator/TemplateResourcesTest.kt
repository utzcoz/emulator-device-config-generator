package com.utzcoz.emulator.device.generator

import com.android.dvlib.DeviceSchema
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import javax.xml.parsers.SAXParserFactory

class TemplateResourcesTest {
    @BeforeEach
    fun setUp() {
        System.clearProperty(SAXParserFactory::class.java.name)
    }

    @AfterEach
    fun tearDown() {
        System.clearProperty(SAXParserFactory::class.java.name)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testTemplateResourceFormat(templateName: String) {
        val templatePath = "${File.separator}templates${File.separator}$templateName"
        val url = Device::class.java.getResource(templatePath)
        val file = File(url.toURI())
        assertEquals(null, System.getProperty(SAXParserFactory::class.java.name))
        // We use dom4j, and it uses pull-parser, what contains SAXParserFactory implementation,
        // that will be used by program default. But pull-parser doesn't support setSchema, which
        // method the dvlib needs, so we change the default SAXParserFactory implementation
        // to JDK runtime version for dvlib to fix error.
        System.setProperty(
            SAXParserFactory::class.java.name,
            "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl"
        )
        ByteArrayInputStream(file.readBytes()).use {
            val fis = it
            ByteArrayOutputStream().use {
                val result = DeviceSchema.validate(fis, it, file.parentFile)
                assertTrue(result)
            }
        }
    }

    companion object {
        @JvmStatic
        fun templates() =
            listOf(
                Arguments.of("automotive-device.xml"),
                Arguments.of("tablet-device.xml"),
                Arguments.of("tv-device.xml")
            )
    }
}