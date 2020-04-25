package com.utzcoz.emulator.device.generator.type

enum class PixelDensity(val type: String) {
    LDPI("ldpi"),
    MDPI("mdpi"),
    TVDPI("tvdpi"),
    HDPI("hdpi"),
    DPI260("260dpi"),
    DPI280("280dpi"),
    DPI300("300dpi"),
    XHDPI("xhdpi"),
    DPI340("340dpi"),
    DPI360("360dpi"),
    DPI400("400dpi"),
    DPI420("420dpi"),
    DPI440("440dpi"),
    XXHDPI("xxhdpi"),
    DPI560("560dpi"),
    XXXHDPI("xxxhdpi");

    companion object {
        fun getPixelDensityType(inputType: String): PixelDensity {
            for (pixelDensity in values()) {
                if (pixelDensity.type == inputType) {
                    return pixelDensity
                }
            }
            throw IllegalArgumentException("Don't support pixel density type $inputType")
        }
    }
}