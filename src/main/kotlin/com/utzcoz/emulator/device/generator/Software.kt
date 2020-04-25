package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.type.BluetoothProfileType
import org.dom4j.Element

class Software {
    var apiLevel: String = "28-"
    var liveWallpaperSupport: Boolean = false
    var bluetoothProfileTypes: BluetoothProfileTypes =
        BluetoothProfileTypes("")
    var glVersion: Float = 3.1F
    var glExtensions: GlExtensions =
        GlExtensions("")
    var statusBar: Boolean = true

    fun parse(softwareElement: Element) {
        for (element in softwareElement.elementIterator()) {
            when (element.name) {
                "api-level" -> {
                    apiLevel = element.textTrim
                    assert("[\\d]*-[\\d]*|[\\d]+".toRegex().matches(apiLevel))
                }
                "live-wallpaper-support" -> liveWallpaperSupport = element.textTrim!!.toBoolean()
                "gl-version" -> {
                    glVersion = element.textTrim!!.toFloat()
                    assert("[0-9]\\.[0-9]".toRegex().matches(element.textTrim))
                }
                "gl-extensions" -> glExtensions =
                    GlExtensions(element.text)
                "status-bar" -> statusBar = element.textTrim!!.toBoolean()
                "bluetooth-profiles" -> bluetoothProfileTypes =
                    BluetoothProfileTypes(element.text)
            }
        }
    }
}

class GlExtensions(glExtensionsString: String) {
    val extensions: List<String> = generateGlExtensions(glExtensionsString)

    companion object {
        private fun generateGlExtensions(glExtensionsString: String) =
            glExtensionsString
                .lines()
                .map { s -> s.trim() }
                .filter { s -> s.isNotEmpty() }
                .toList()
    }
}

class BluetoothProfileTypes(bluetoothProfilesString: String) {
    val bluetoothProfileTypes: List<BluetoothProfileType> = generateBluetoothProfiles(bluetoothProfilesString)

    companion object {
        fun generateBluetoothProfiles(bluetoothProfilesString: String) =
            bluetoothProfilesString
                .lines()
                .map { s -> s.trim() }
                .filter { s -> s.isNotEmpty() }
                .map { s ->
                    BluetoothProfileType.getBluetoothProfileType(
                        s
                    )
                }
                .toList()
    }
}