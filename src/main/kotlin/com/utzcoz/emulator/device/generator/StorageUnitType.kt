package com.utzcoz.emulator.device.generator

enum class StorageUnitType(val type: String) {
    B("B"),
    KB("KiB"),
    MB("MiB"),
    GB("GiB"),
    TB("TiB");

    companion object {
        fun getStorageUnitType(inputType: String): StorageUnitType {
            when (inputType) {
                B.type -> return B
                KB.type -> return KB
                MB.type -> return MB
                GB.type -> return GB
                TB.type -> return TB
            }
            throw IllegalArgumentException("Don't support storage unit type $inputType")
        }
    }
}