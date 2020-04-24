package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.NavType.DPAD
import com.utzcoz.emulator.device.generator.NavType.NO_NAV
import com.utzcoz.emulator.device.generator.NavType.TRACKBALL
import com.utzcoz.emulator.device.generator.NavType.WHEEL
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class NavTypeTest {
    @ParameterizedTest
    @MethodSource("navTypes")
    fun testGetNavTypeWithSupportedTypes(inputType: String, navType: NavType) {
        assertThat(NavType.getNavType(inputType), hamis(navType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedNavTypes")
    fun testGetNavTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support nav type $inputType") {
            NavType.getNavType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun navTypes() = listOf(
            Arguments.of(DPAD.type, DPAD),
            Arguments.of(TRACKBALL.type, TRACKBALL),
            Arguments.of(WHEEL.type, WHEEL),
            Arguments.of(NO_NAV.type, NO_NAV)
        )

        @JvmStatic
        fun unsupportedNavTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(DPAD.type + "-unsupported"),
            Arguments.of(TRACKBALL.type + "-unsupported"),
            Arguments.of(WHEEL.type + "-unsupported"),
            Arguments.of(NO_NAV.type + "-unsupported")
        )
    }
}