package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.DockType.CAR
import com.utzcoz.emulator.device.generator.DockType.DESK
import com.utzcoz.emulator.device.generator.DockType.TV
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.hamcrest.CoreMatchers.`is` as hamis

class DockTypeTest {
    @ParameterizedTest
    @MethodSource("dockTypes")
    fun testGetDockTypeWithSupportedTypes(inputType: String, dockType: DockType) {
        assertThat(DockType.getDockType(inputType), hamis(dockType))
    }

    @ParameterizedTest
    @MethodSource("unsupportedDockTypes")
    fun testGetDockTypeWithUnsupportedTypes(inputType: String) {
        assertThrows<IllegalArgumentException>("Don't support dock type $inputType") {
            DockType.getDockType(inputType)
        }
    }

    companion object {
        @JvmStatic
        fun dockTypes() = listOf(
            Arguments.of(DESK.type, DESK),
            Arguments.of(TV.type, TV),
            Arguments.of(CAR.type, CAR)
        )

        @JvmStatic
        fun unsupportedDockTypes() = listOf(
            Arguments.of("unsupported"),
            Arguments.of(DESK.type + "-unsupported"),
            Arguments.of(TV.type + "-unsupported"),
            Arguments.of(CAR.type + "-unsupported")
        )

    }
}