package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.hardware.PixelDensity
import com.utzcoz.emulator.device.generator.hardware.ScreenRatio
import com.utzcoz.emulator.device.generator.hardware.ScreenSize
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.control.TitledPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage

class DeviceGenerator : Application() {
    companion object {
        private const val DEFAULT_BUTTON_WIDTH: Double = 150.0
        private const val DEFAULT_VBOX_SPACING: Double = 10.0
        private const val DEFAULT_HBOX_SPACING: Double = 5.0

        private const val ID_NAME: String = "name"
        private const val ID_ID: String = "id"
        private const val ID_MANUFACTURER: String = "manufacturer"
        private const val ID_SCREEN_SIZE: String = "screen-size"
        private const val ID_PIXEL_DENSITY: String = "pixel-density"
        private const val ID_SCREEN_RATIO: String = "screen-ratio"
        private const val ID_X_DIMENSION: String = "x-dimension"
        private const val ID_Y_DIMENSION: String = "y-dimension"
        private const val ID_X_DPI: String = "xdpi"
        private const val ID_Y_DPI: String = "ydpi"
    }

    private fun generateOneLineTextField(id: String, hint: String, value: String): HBox =
        HBox(DEFAULT_HBOX_SPACING, Label(hint), TextField(value).apply { this.id = id })

    private fun addOneLineTextFiled(vbox: VBox, id: String, value: String) {
        vbox.children.add(generateOneLineTextField(id, id, value))
    }

    private fun addComboBox(vbox: VBox, id: String, values: ObservableList<String>, defaultValue: String) {
        val comboBox = ComboBox(values).apply {
            this.id = id
        }
        comboBox.selectionModel.select(defaultValue)
        val hbox = HBox(DEFAULT_HBOX_SPACING, Label(id), comboBox)
        vbox.children.add(hbox)
    }

    private fun generateHardwareGroup(vbox: VBox, device: Device) {
        val hardwareGroup = VBox(DEFAULT_VBOX_SPACING)
        addComboBox(
            hardwareGroup,
            ID_SCREEN_SIZE,
            FXCollections.observableList(
                ScreenSize.values().map { value -> value.type }.toList()
            ),
            device.hardware.screen.screenSize.type
        )
        addComboBox(
            hardwareGroup,
            ID_PIXEL_DENSITY,
            FXCollections.observableList(
                PixelDensity.values().map { value -> value.type }.toList()
            ),
            device.hardware.screen.pixelDensity.type
        )
        addComboBox(
            hardwareGroup,
            ID_SCREEN_RATIO,
            FXCollections.observableList(
                ScreenRatio.values().map { value -> value.type }.toList()
            ),
            device.hardware.screen.screenRatio.type
        )
        addOneLineTextFiled(hardwareGroup, ID_X_DIMENSION, device.hardware.screen.dimensions.xDimension.toString())
        addOneLineTextFiled(hardwareGroup, ID_Y_DIMENSION, device.hardware.screen.dimensions.yDimension.toString())
        addOneLineTextFiled(hardwareGroup, ID_X_DPI, device.hardware.screen.xdpi.toString())
        addOneLineTextFiled(hardwareGroup, ID_Y_DPI, device.hardware.screen.ydpi.toString())
        val hardwarePane = TitledPane("Hardware", hardwareGroup)
        hardwarePane.isCollapsible = true
        hardwarePane.isExpanded = true
        vbox.children.add(hardwarePane)
    }

    private fun generateDeviceConfig(primaryStage: Stage, vbox: VBox, template: String) {
        val fileChooser = FileChooser()
        fileChooser.title = "Select file to store the new config"
        fileChooser.initialFileName = "new-device.xml"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("XML File", "*.xml"))
        val saveFile = fileChooser.showSaveDialog(primaryStage)
        println("save file ${saveFile.absolutePath}")
    }

    private fun switchToTemplatePage(primaryStage: Stage, template: String) {
        val root = BorderPane()
        val device = Device.readTemplate(template)
        val scrollPane = ScrollPane()
        val vbox = VBox(DEFAULT_VBOX_SPACING)
        vbox.children.add(Button("Back").apply {
            setOnAction {
                switchToMainPage(primaryStage)
            }
        })
        addOneLineTextFiled(vbox, ID_NAME, device.name)
        addOneLineTextFiled(vbox, ID_ID, device.id)
        addOneLineTextFiled(vbox, ID_MANUFACTURER, device.manufacturer)
        generateHardwareGroup(vbox, device)
        vbox.children.add(Button("Generate").apply {
            setOnAction {
                generateDeviceConfig(primaryStage, vbox, template)
            }
        })
        scrollPane.content = vbox
        root.center = scrollPane
        primaryStage.scene = Scene(root, 400.0, 600.0)
    }

    private fun generateTemplateButton(primaryStage: Stage, vbox: VBox, template: String) {
        val button = Button(template).apply {
            prefWidth = DEFAULT_BUTTON_WIDTH
            setOnAction {
                switchToTemplatePage(primaryStage, "${text.toLowerCase()}-device.xml")
            }
        }
        vbox.children.add(button)
    }

    private fun switchToMainPage(primaryStage: Stage) {
        val root = BorderPane()
        val vbox = VBox(DEFAULT_VBOX_SPACING).apply {
            prefWidth = 400.0
            prefHeight = 400.0
        }
        val hint = Label("Select template to start").apply {
            prefWidth = 200.0
        }
        vbox.children.addAll(hint)
        generateTemplateButton(primaryStage, vbox, "Automotive")
        generateTemplateButton(primaryStage, vbox, "Tablet")
        generateTemplateButton(primaryStage, vbox, "TV")
        root.center = vbox
        primaryStage.scene = Scene(root, 400.0, 600.0)
        primaryStage.title = "Emulator device config generator"
        primaryStage.show()
    }

    override fun start(primaryStage: Stage) {
        switchToMainPage(primaryStage)
    }
}

fun main(args: Array<String>) {
    Application.launch(DeviceGenerator::class.java)
}