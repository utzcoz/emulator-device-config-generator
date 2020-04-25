package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.type.MechanismType
import com.utzcoz.emulator.device.generator.type.MultiTouchType
import com.utzcoz.emulator.device.generator.type.PixelDensity
import com.utzcoz.emulator.device.generator.type.ScreenRatio
import com.utzcoz.emulator.device.generator.type.ScreenSize
import com.utzcoz.emulator.device.generator.type.ScreenType
import org.dom4j.Element

class Screen {
    var screenSize: ScreenSize = ScreenSize.SMALL
    var diagonalLength: Float = -1F
    var pixelDensity: PixelDensity = PixelDensity.MDPI
    var screenRatio: ScreenRatio = ScreenRatio.NOT_LONG
    var dimensions: Dimensions = Dimensions()
    var xdpi: Float = 0F
    var ydpi: Float = 0F
    var touch: Touch = Touch()

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
                "touch" -> touch.parse(element)
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

class Touch {
    var multiTouchType: MultiTouchType = MultiTouchType.NONE
    var mechanismType: MechanismType = MechanismType.NOT_TOUCH
    var screenType: ScreenType = ScreenType.NO_TOUCH

    fun parse(touchElement: Element) {
        for (element in touchElement.elementIterator()) {
            when (element.name) {
                "multitouch" -> multiTouchType =
                    MultiTouchType.getMultiTouchType(
                        element.text
                    )
                "mechanism" -> mechanismType =
                    MechanismType.getMechanismType(
                        element.text
                    )
                "screen-type" -> screenType =
                    ScreenType.getScreenType(
                        element.text
                    )
            }
        }
    }
}