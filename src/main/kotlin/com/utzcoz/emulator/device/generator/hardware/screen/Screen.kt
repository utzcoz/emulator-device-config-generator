package com.utzcoz.emulator.device.generator.hardware.screen

import org.dom4j.Element

class Screen {
    var screenSize: ScreenSize = ScreenSize.SMALL
    var diagonalLength: Float = -1F
    var pixelDensity: PixelDensity = PixelDensity.MDPI
    var screenRatio: ScreenRatio = ScreenRatio.NOT_LONG
    var dimensions: Dimensions = Dimensions()
    var xdpi: Float = 0F
    var ydpi: Float = 0F

    fun parse(screenElement: Element) {
        for (element in screenElement.elementIterator()) {
            when (element.name) {
                "screen-size" -> screenSize =
                    ScreenSize.getScreenSizeType(
                        element.text
                    )
                "diagonal-length" -> diagonalLength = element.textTrim.toFloat()
                "pixel-density" -> pixelDensity =
                    PixelDensity.getPixelDensityType(
                        element.text
                    )
                "screen-ratio" -> screenRatio =
                    ScreenRatio.getScreenRatioType(
                        element.text
                    )
                "dimensions" -> dimensions.parse(element)
                "xdpi" -> xdpi = element.textTrim.toFloat()
                "ydpi" -> ydpi = element.textTrim.toFloat()
            }
        }
    }
}

class Dimensions {
    var xDimension: Int = 0
    var yDimension: Int = 0

    fun parse(dimensionsElement: Element) {
        for (element in dimensionsElement.elementIterator()) {
            when (element.name) {
                "x-dimension" -> xDimension = element.textTrim.toInt()
                "y-dimension" -> yDimension = element.textTrim.toInt()
            }
        }
    }
}