package com.utzcoz.emulator.device.generator.type

enum class BluetoothProfileType(val type: String) {
    A2DP("A2DP"),
    ATT("ATT"),
    AVRCP("AVRCP"),
    AVDTP("AVDTP"),
    BIP("BIP"),
    BPP("BPP"),
    CIP("CIP"),
    CTP("CTP"),
    DIP("DIP"),
    DUN("DUN"),
    FAX("FAX"),
    FTP("FTP"),
    GAVDP("GAVDP"),
    GAP("GAP"),
    GATT("GATT"),
    GOEP("GOEP"),
    HCRP("HCRP"),
    HDP("HDP"),
    HFP("HFP"),
    HID("HID"),
    HSP("HSP"),
    ICP("ICP"),
    LAP("LAP"),
    MAP("MAP"),
    OPP("OPP"),
    PAN("PAN"),
    PBA("PBA"),
    PBAP("PBAP"),
    SPP("SPP"),
    SDAP("SDAP"),
    SAP("SAP"),
    SIM("SIM"),
    RSAP("rSAP"),
    SYNCH("SYNCH"),
    VDP("VDP"),
    WAPB("WAPB");

    companion object {
        fun getBluetoothProfileType(inputType: String): BluetoothProfileType {
            for (bluetoothProfileType in values()) {
                if (bluetoothProfileType.type == inputType) {
                    return bluetoothProfileType
                }
            }
            throw IllegalArgumentException("Don't support bluetooth profile type $inputType")
        }
    }
}