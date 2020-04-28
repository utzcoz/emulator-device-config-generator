package com.utzcoz.emulator.device.generator.hardware

import org.dom4j.Element

class Screen {
    var screenSize: ScreenSize = ScreenSize.SMALL
    var pixelDensity: PixelDensity = PixelDensity.MDPI
    var screenRatio: ScreenRatio = ScreenRatio.NOT_LONG
    var dimensions: Dimensions = Dimensions()
    var xdpi: Float = 0F
    var ydpi: Float = 0F

    fun parse(screenElement: Element) {
        for (element in screenElement.elementIterator()) {
            when (element.name) {
                "screen-size" -> screenSize = ScreenSize.getScreenSizeType(element.text)
                "pixel-density" -> pixelDensity = PixelDensity.getPixelDensityType(element.text)
                "screen-ratio" -> screenRatio = ScreenRatio.getScreenRatioType(element.text)
                "dimensions" -> dimensions.parse(element)
                "xdpi" -> xdpi = element.textTrim.toFloat()
                "ydpi" -> ydpi = element.textTrim.toFloat()
            }
        }
    }

    fun save(screenElement: Element) {
        for (element in screenElement.elementIterator()) {
            when (element.name) {
                "screen-size" -> element.text = screenSize.type
                "pixel-density" -> element.text = pixelDensity.type
                "screen-ratio" -> element.text = screenRatio.type
                "dimensions" -> dimensions.save(element)
                "xdpi" -> element.text = xdpi.toString()
                "ydpi" -> element.text = ydpi.toString()
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

    fun save(dimensionsElement: Element) {
        for (element in dimensionsElement.elementIterator()) {
            when (element.name) {
                "x-dimension" -> element.text = xDimension.toString()
                "y-dimension" -> element.text = yDimension.toString()
            }
        }
    }
}