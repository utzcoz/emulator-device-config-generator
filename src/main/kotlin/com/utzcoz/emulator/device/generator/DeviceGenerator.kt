package com.utzcoz.emulator.device.generator

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.launch
import java.io.File

class DeviceGenerator : Application() {
    override fun start(primaryStage: Stage) {
        val scenePath = "${File.separator}layout${File.separator}scene.fxml"
        val root = FXMLLoader.load<Parent>(DeviceGenerator::class.java.getResource(scenePath))
        primaryStage.title = "Hello world"
        primaryStage.scene = Scene(root, 300.0, 275.0)
        primaryStage.show()
    }
}

class Controller {

}

fun main(args: Array<String>) {
    launch<DeviceGenerator>(args)
}