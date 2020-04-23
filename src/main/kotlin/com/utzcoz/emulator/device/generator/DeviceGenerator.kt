package com.utzcoz.emulator.device.generator

import javafx.scene.text.FontWeight
import tornadofx.App
import tornadofx.Stylesheet
import tornadofx.View
import tornadofx.c
import tornadofx.hbox
import tornadofx.label
import tornadofx.launch
import tornadofx.px

class DeviceGenerator : App(DeviceGeneratorView::class, Styles::class)

class DeviceGeneratorView : View("DeviceGeneratorView") {
    override val root = hbox {
        label("Hello world")
    }
}

class Styles : Stylesheet() {
    init {
        Companion.label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#cecece")
        }
    }
}

fun main(args: Array<String>) {
    launch<DeviceGenerator>(args)
}