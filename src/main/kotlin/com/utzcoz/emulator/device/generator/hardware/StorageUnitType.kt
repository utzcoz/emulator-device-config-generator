package com.utzcoz.emulator.device.generator.hardware

enum class StorageUnitType(val type: String) {
    B("B"),
    KB("KiB"),
    MB("MiB"),
    GB("GiB"),
    TB("TiB");

    companion object {
        fun getStorageUnitType(inputType: String): StorageUnitType {
            for (storageUnitType in values()) {
                if (storageUnitType.type == inputType) {
                    return storageUnitType
                }
            }
            throw IllegalArgumentException("Don't support storage unit type $inputType")
        }
    }
}