package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.type.KeyboardState
import com.utzcoz.emulator.device.generator.type.NavState
import com.utzcoz.emulator.device.generator.type.ScreenOrientation
import org.dom4j.Element

class State {
    var default: Boolean = false
    var name: String = ""
    var description: String = ""
    var screenOrientation: ScreenOrientation = ScreenOrientation.PORTRAIT
    var keyboardState: KeyboardState = KeyboardState.KEYS_SOFT
    var navState: NavState = NavState.NO_NAV

    fun parse(stateElement: Element) {
        val defaultAttribute = stateElement.attribute("default")
        if (defaultAttribute != null) {
            val defaultContent = defaultAttribute.text
            if (!defaultContent.isNullOrBlank() && !defaultContent.isNullOrEmpty()) {
                default = defaultContent.trim().toBoolean()
            }
        }
        name = stateElement.attribute("name").text!!
        for (element in stateElement.elementIterator()) {
            when (element.name) {
                "description" -> description = element.textTrim
                "screen-orientation" ->
                    screenOrientation =
                        ScreenOrientation.getScreenOrientationType(
                            element.textTrim
                        )
                "keyboard-state" -> keyboardState =
                    KeyboardState.getKeyboardState(
                        element.textTrim
                    )
                "nav-state" -> navState =
                    NavState.getNavState(element.textTrim)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is State) {
            return false
        }
        return default == other.default
                && name == other.name
                && description == other.description
                && screenOrientation == other.screenOrientation
                && keyboardState == other.keyboardState
                && navState == other.navState
    }

    override fun hashCode(): Int {
        return ((((default.hashCode() * 31
                + name.hashCode()) * 31
                + description.hashCode()) * 31
                + screenOrientation.hashCode()) * 31
                + keyboardState.hashCode()) * 31 + navState.hashCode()
    }

    override fun toString(): String {
        return "State [ default: $default, name: $name, " +
                "description $description, " +
                "screen orientation: $screenOrientation, " +
                "keyboard state: $keyboardState, " +
                "nav state: $navState ]"
    }
}