package com.utzcoz.emulator.device.generator.type

enum class Networking(val type: String) {
    NFC("NFC"),
    BLUETOOTH("Bluetooth"),
    WIFI("Wifi");

    companion object {
        fun getNetworkingType(inputType: String): Networking {
            for (networking in values()) {
                if (networking.type == inputType) {
                    return networking
                }
            }
            throw IllegalArgumentException("Don't support networking $inputType")
        }
    }
}