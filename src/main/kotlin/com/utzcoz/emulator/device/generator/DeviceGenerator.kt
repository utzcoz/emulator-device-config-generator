package com.utzcoz.emulator.device.generator

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage

class DeviceGenerator : Application() {
    private fun generateOneLineTextField(id: String, hint: String, value: String): HBox =
        HBox(5.0, Label(hint), TextField(value).apply { this.id = id })

    private fun addOneLineTextFiled(vbox: VBox, id: String, hint: String, value: String) {
        vbox.children.add(generateOneLineTextField(id, hint, value))
    }

    private fun switchToTemplatePage(primaryStage: Stage, template: String) {
        val root = BorderPane()
        val device = Device.readTemplate(template)
        val scrollPane = ScrollPane()
        val vbox = VBox(10.0)
        addOneLineTextFiled(vbox, "name", "name", device.name)
        addOneLineTextFiled(vbox, "id", "id", device.id)
        addOneLineTextFiled(vbox, "manufacturer", "manufacturer", device.manufacturer)

        scrollPane.content = vbox
        root.center = scrollPane
        primaryStage.scene = Scene(root, 800.0, 800.0)
    }

    override fun start(primaryStage: Stage) {
        val root = BorderPane()
        val vbox = VBox(10.0).apply {
            prefWidth = 400.0
            prefHeight = 400.0
        }
        val hint = Label("Select template to start").apply {
            prefWidth = 200.0
        }
        val automotiveButton = Button("Automotive").apply {
            prefWidth = 150.0
            setOnAction {
                switchToTemplatePage(primaryStage, "${text.toLowerCase()}-device.xml")
            }
        }
        val tabletButton = Button("tablet").apply {
            prefWidth = 150.0
            setOnAction {
                switchToTemplatePage(primaryStage, "${text.toLowerCase()}-device.xml")
            }
        }
        val tvButton = Button("TV").apply {
            prefWidth = 150.0
            setOnAction {
                switchToTemplatePage(primaryStage, "${text.toLowerCase()}-device.xml")
            }
        }
        vbox.children.addAll(hint, automotiveButton, tabletButton, tvButton)
        root.center = vbox
        primaryStage.scene = Scene(root, 800.0, 800.0)
        primaryStage.title = "Emulator device config generator"
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(DeviceGenerator::class.java)
}