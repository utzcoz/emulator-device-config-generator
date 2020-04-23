package com.utzcoz.emulator.device.generator

import java.lang.IllegalArgumentException

/**
 * The networking type based on com.android.dvlib.devices-5.xsd.
 */
enum class Networking(val type: String) {
    NFC("NFC"),
    BLUETOOTH("Bluetooth"),
    WIFI("Wifi");

    companion object {
        fun getNetworkingType(inputType: String): Networking {
            when (inputType) {
                NFC.type -> return NFC;
                BLUETOOTH.type -> return BLUETOOTH;
                WIFI.type -> return WIFI;
            }
            throw IllegalArgumentException("Don't support networking $inputType")
        }
    }
}