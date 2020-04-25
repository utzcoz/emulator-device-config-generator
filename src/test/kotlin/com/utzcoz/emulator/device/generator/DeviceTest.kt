package com.utzcoz.emulator.device.generator

import com.utzcoz.emulator.device.generator.type.ButtonsType
import com.utzcoz.emulator.device.generator.type.CameraLocation
import com.utzcoz.emulator.device.generator.type.KeyboardType
import com.utzcoz.emulator.device.generator.type.MechanismType
import com.utzcoz.emulator.device.generator.type.MultiTouchType
import com.utzcoz.emulator.device.generator.type.NavType
import com.utzcoz.emulator.device.generator.type.PixelDensity
import com.utzcoz.emulator.device.generator.type.PowerType
import com.utzcoz.emulator.device.generator.type.ScreenRatio
import com.utzcoz.emulator.device.generator.type.ScreenSize
import com.utzcoz.emulator.device.generator.type.ScreenType
import com.utzcoz.emulator.device.generator.type.StorageUnitType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DeviceTest {
    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceBasicInfo(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.name, actual.name)
        assertEquals(expected.id, actual.id)
        assertEquals(expected.manufacturer, actual.manufacturer)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceHardwareScreen(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.hardware.screen.screenSize, actual.hardware.screen.screenSize)
        assertEquals(expected.hardware.screen.diagonalLength, actual.hardware.screen.diagonalLength)
        assertEquals(expected.hardware.screen.pixelDensity, actual.hardware.screen.pixelDensity)
        assertEquals(expected.hardware.screen.screenRatio, actual.hardware.screen.screenRatio)
        assertEquals(expected.hardware.screen.dimensions.xDimension, actual.hardware.screen.dimensions.xDimension)
        assertEquals(expected.hardware.screen.dimensions.yDimension, actual.hardware.screen.dimensions.yDimension)
        assertEquals(expected.hardware.screen.xdpi, actual.hardware.screen.xdpi)
        assertEquals(expected.hardware.screen.ydpi, actual.hardware.screen.ydpi)
        assertEquals(expected.hardware.screen.touch.multiTouchType, actual.hardware.screen.touch.multiTouchType)
        assertEquals(expected.hardware.screen.touch.mechanismType, actual.hardware.screen.touch.mechanismType)
        assertEquals(expected.hardware.screen.touch.screenType, actual.hardware.screen.touch.screenType)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceHardware(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.hardware.networkingList.networkingList, actual.hardware.networkingList.networkingList)
        assertEquals(expected.hardware.sensors.sensors, actual.hardware.sensors.sensors)
        assertEquals(expected.hardware.mic, actual.hardware.mic)
        assertTrue(actual.hardware.mic)
        assertEquals(expected.hardware.cameras, actual.hardware.cameras)
        assertEquals(expected.hardware.keyboardType, actual.hardware.keyboardType)
        assertEquals(expected.hardware.navType, actual.hardware.navType)
        assertEquals(expected.hardware.buttonsType, actual.hardware.buttonsType)
        assertEquals(expected.hardware.cpu, actual.hardware.cpu)
        assertEquals(expected.hardware.gpu, actual.hardware.gpu)
        assertEquals(expected.hardware.abiList.abiList, actual.hardware.abiList.abiList)
        assertEquals(expected.hardware.powerType, actual.hardware.powerType)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceHardwareStorage(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.hardware.ramSize, actual.hardware.ramSize)
        assertEquals(expected.hardware.ramUnit, actual.hardware.ramUnit)
        assertEquals(expected.hardware.internalStorageSize, actual.hardware.internalStorageSize)
        assertEquals(expected.hardware.internalStorageUnit, actual.hardware.internalStorageUnit)
        assertEquals(expected.hardware.removableStorageSize, actual.hardware.removableStorageSize)
        assertEquals(expected.hardware.removableStorageUnit, actual.hardware.removableStorageUnit)
    }

    @ParameterizedTest
    @MethodSource("templates")
    fun testReadTemplateWithExistTemplatesForDeviceSoftware(templateName: String, expected: Device) {
        val actual = Device.readTemplate(templateName)
        assertEquals(expected.software.apiLevel, actual.software.apiLevel)
        assertEquals(expected.software.liveWallpaperSupport, actual.software.liveWallpaperSupport)
        assertEquals(0, actual.software.bluetoothProfileTypes.bluetoothProfileTypes.size)
        assertEquals(
            expected.software.bluetoothProfileTypes.bluetoothProfileTypes,
            actual.software.bluetoothProfileTypes.bluetoothProfileTypes
        )
        assertEquals(expected.software.glVersion, actual.software.glVersion)
        assertEquals(expected.software.glExtensions.extensions, actual.software.glExtensions.extensions)
        assertEquals(expected.software.statusBar, actual.software.statusBar)
    }

    companion object {
        @JvmStatic
        private fun generateAutomotiveDevice(): Device {
            val automotiveDevice = Device()
            automotiveDevice.name = "Automotive Template"
            automotiveDevice.id = "automotive_template"
            automotiveDevice.manufacturer = "Generic"
            automotiveDevice.hardware = Hardware()
            automotiveDevice.hardware.screen = Screen()
            automotiveDevice.hardware.screen.screenSize = ScreenSize.NORMAL
            automotiveDevice.hardware.screen.diagonalLength = 8.4F
            automotiveDevice.hardware.screen.pixelDensity = PixelDensity.MDPI
            automotiveDevice.hardware.screen.screenRatio = ScreenRatio.NOT_LONG
            automotiveDevice.hardware.screen.dimensions = Dimensions()
            automotiveDevice.hardware.screen.dimensions.xDimension = 1024
            automotiveDevice.hardware.screen.dimensions.yDimension = 768
            automotiveDevice.hardware.screen.xdpi = 152.00F
            automotiveDevice.hardware.screen.ydpi = 152.00F
            automotiveDevice.hardware.screen.touch.multiTouchType = MultiTouchType.BASIC
            automotiveDevice.hardware.screen.touch.mechanismType = MechanismType.FINGER
            automotiveDevice.hardware.screen.touch.screenType = ScreenType.CAPACITIVE
            val networkingListString = """
                Bluetooth
                Wifi
                NFC
            """.trimIndent()
            automotiveDevice.hardware.networkingList = NetworkingList(networkingListString)
            val sensorsString = """
                GPS
                LightSensor
            """.trimIndent()
            automotiveDevice.hardware.sensors = Sensors(sensorsString)
            automotiveDevice.hardware.mic = true
            automotiveDevice.hardware.keyboardType = KeyboardType.NO_KEYS
            automotiveDevice.hardware.ramUnit = StorageUnitType.KB
            automotiveDevice.hardware.ramSize = 3774492
            automotiveDevice.hardware.internalStorageUnit = StorageUnitType.KB
            automotiveDevice.hardware.internalStorageSize = 10255672
            automotiveDevice.hardware.removableStorageUnit = StorageUnitType.TB
            val abiListString = """
                armeabi-v7a
                x86
            """.trimIndent()
            automotiveDevice.hardware.abiList = AbiList(abiListString)
            automotiveDevice.software.apiLevel = "28-"
            automotiveDevice.software.glVersion = 2.0F
            return automotiveDevice
        }

        @JvmStatic
        private fun generateTabletDevice(): Device {
            val tabletDevice = Device()
            tabletDevice.name = "Tablet Template"
            tabletDevice.id = "tablet_template"
            tabletDevice.manufacturer = "Generic"
            tabletDevice.hardware.screen.screenSize = ScreenSize.XLARGE
            tabletDevice.hardware.screen.diagonalLength = 13.50F
            tabletDevice.hardware.screen.pixelDensity = PixelDensity.HDPI
            tabletDevice.hardware.screen.screenRatio = ScreenRatio.LONG
            tabletDevice.hardware.screen.dimensions.xDimension = 2560
            tabletDevice.hardware.screen.dimensions.yDimension = 1440
            tabletDevice.hardware.screen.xdpi = 240.00F
            tabletDevice.hardware.screen.ydpi = 240.00F
            tabletDevice.hardware.screen.touch.multiTouchType = MultiTouchType.JAZZ_HANDS
            tabletDevice.hardware.screen.touch.mechanismType = MechanismType.FINGER
            tabletDevice.hardware.screen.touch.screenType = ScreenType.CAPACITIVE
            val networkingListString = """
                Bluetooth
                Wifi
                NFC
            """.trimIndent()
            tabletDevice.hardware.networkingList = NetworkingList(networkingListString)
            val sensorsString = """
                Accelerometer
                Barometer
                Compass
                GPS
                Gyroscope
                LightSensor
                ProximitySensor
            """.trimIndent()
            tabletDevice.hardware.sensors = Sensors(sensorsString)
            tabletDevice.hardware.mic = true
            val cameraFront = Camera()
            cameraFront.location = CameraLocation.FRONT
            cameraFront.autoFocus = true
            cameraFront.flash = false
            val cameraBack = Camera()
            cameraBack.location = CameraLocation.BACK
            cameraBack.autoFocus = true
            cameraBack.flash = true
            tabletDevice.hardware.cameras.plus(cameraFront).plus(cameraBack)
            tabletDevice.hardware.keyboardType = KeyboardType.NO_KEYS
            tabletDevice.hardware.ramUnit = StorageUnitType.GB
            tabletDevice.hardware.ramSize = 4
            tabletDevice.hardware.internalStorageSize = 64
            tabletDevice.hardware.internalStorageUnit = StorageUnitType.GB
            tabletDevice.hardware.removableStorageUnit = StorageUnitType.TB
            tabletDevice.hardware.cpu = "Qualcomm Snapdragon 845"
            tabletDevice.hardware.gpu = "Adreno 630"
            val abiListString = """
                armeabi
                armeabi-v7a
                arm64-v8a
            """.trimIndent()
            tabletDevice.hardware.abiList = AbiList(abiListString)
            tabletDevice.hardware.powerType = PowerType.BATTERY
            tabletDevice.software.apiLevel = "29-29"
            tabletDevice.software.liveWallpaperSupport = true
            tabletDevice.software.glVersion = 3.2F
            val glExtensionsString = """
                GL_OES_EGL_image
                GL_OES_EGL_image_external
                GL_OES_EGL_sync
                GL_OES_vertex_half_float
                GL_OES_framebuffer_object
                GL_OES_rgb8_rgba8
                GL_OES_compressed_ETC1_RGB8_texture
                GL_AMD_compressed_ATC_texture
                GL_KHR_texture_compression_astc_ldr
                GL_KHR_texture_compression_astc_hdr
                GL_OES_texture_compression_astc
                GL_OES_texture_npot
                GL_EXT_texture_filter_anisotropic
                GL_EXT_texture_format_BGRA8888
                GL_OES_texture_3D
                GL_EXT_color_buffer_float
                GL_EXT_color_buffer_half_float
                GL_QCOM_alpha_test
                GL_OES_depth24
                GL_OES_packed_depth_stencil
                GL_OES_depth_texture
                GL_OES_depth_texture_cube_map
                GL_EXT_sRGB
                GL_OES_texture_float
                GL_OES_texture_float_linear
                GL_OES_texture_half_float
                GL_OES_texture_half_float_linear
                GL_EXT_texture_type_2_10_10_10_REV
                GL_EXT_texture_sRGB_decode
                GL_EXT_texture_format_sRGB_override
                GL_OES_element_index_uint
                GL_EXT_copy_image
                GL_EXT_geometry_shader
                GL_EXT_tessellation_shader
                GL_OES_texture_stencil8
                GL_EXT_shader_io_blocks
                GL_OES_shader_image_atomic
                GL_OES_sample_variables
                GL_EXT_texture_border_clamp
                GL_EXT_multisampled_render_to_texture
                GL_EXT_multisampled_render_to_texture2
                GL_OES_shader_multisample_interpolation
                GL_EXT_texture_cube_map_array
                GL_EXT_draw_buffers_indexed
                GL_EXT_gpu_shader5
                GL_EXT_robustness
                GL_EXT_texture_buffer
                GL_EXT_shader_framebuffer_fetch
                GL_ARM_shader_framebuffer_fetch_depth_stencil
                GL_OES_texture_storage_multisample_2d_array
                GL_OES_sample_shading
                GL_OES_get_program_binary
                GL_EXT_debug_label
                GL_KHR_blend_equation_advanced
                GL_KHR_blend_equation_advanced_coherent
                GL_QCOM_tiled_rendering
                GL_ANDROID_extension_pack_es31a
                GL_EXT_primitive_bounding_box
                GL_OES_standard_derivatives
                GL_OES_vertex_array_object
                GL_EXT_disjoint_timer_query
                GL_KHR_debug
                GL_EXT_YUV_target
                GL_EXT_sRGB_write_control
                GL_EXT_texture_norm16
                GL_EXT_discard_framebuffer
                GL_OES_surfaceless_context
                GL_OVR_multiview
                GL_OVR_multiview2
                GL_EXT_texture_sRGB_R8
                GL_KHR_no_error
                GL_EXT_debug_marker
                GL_OES_EGL_image_external_essl3
                GL_OVR_multiview_multisampled_render_to_texture
                GL_EXT_buffer_storage
                GL_EXT_external_buffer
                GL_EXT_blit_framebuffer_params
                GL_EXT_clip_cull_distance
                GL_EXT_protected_textures
                GL_EXT_shader_non_constant_global_initializers
                GL_QCOM_texture_foveated
                GL_QCOM_shader_framebuffer_fetch_noncoherent
                GL_EXT_memory_object
                GL_EXT_memory_object_fd
                GL_EXT_EGL_image_array
                GL_NV_shader_noperspective_interpolation
                GL_KHR_robust_buffer_access_behavior
                GL_EXT_EGL_image_storage
                GL_EXT_clip_control
            """.trimIndent()
            tabletDevice.software.glExtensions = GlExtensions(glExtensionsString)
            return tabletDevice
        }

        @JvmStatic
        private fun generateTVDevice(): Device {
            val tvDevice = Device()
            tvDevice.name = "TV Template"
            tvDevice.name = "TV Template"
            tvDevice.id = "tv_template"
            tvDevice.manufacturer = "Generic"
            tvDevice.hardware.screen.screenSize = ScreenSize.XLARGE
            tvDevice.hardware.screen.diagonalLength = 55.00F
            tvDevice.hardware.screen.pixelDensity = PixelDensity.XHDPI
            tvDevice.hardware.screen.screenRatio = ScreenRatio.LONG
            tvDevice.hardware.screen.dimensions.xDimension = 1920
            tvDevice.hardware.screen.dimensions.yDimension = 1080
            tvDevice.hardware.screen.xdpi = 40.05F
            tvDevice.hardware.screen.ydpi = 40.05F
            tvDevice.hardware.screen.touch.multiTouchType = MultiTouchType.NONE
            tvDevice.hardware.screen.touch.mechanismType = MechanismType.NOT_TOUCH
            tvDevice.hardware.screen.touch.screenType = ScreenType.NO_TOUCH
            val networkingListString = """
                Bluetooth
                Wifi
                NFC
            """.trimIndent()
            tvDevice.hardware.networkingList = NetworkingList(networkingListString)
            val sensorsString = """
                GPS
                LightSensor
            """.trimIndent()
            tvDevice.hardware.sensors = Sensors(sensorsString)
            tvDevice.hardware.mic = true
            tvDevice.hardware.keyboardType = KeyboardType.QWERTY
            tvDevice.hardware.navType = NavType.DPAD
            tvDevice.hardware.ramUnit = StorageUnitType.GB
            tvDevice.hardware.ramSize = 2
            tvDevice.hardware.buttonsType = ButtonsType.HARD
            tvDevice.hardware.internalStorageUnit = StorageUnitType.KB
            tvDevice.hardware.internalStorageSize = 7811891
            tvDevice.hardware.removableStorageUnit = StorageUnitType.TB
            val abiListString = """
                armeabi-v7a
                x86
            """.trimIndent()
            tvDevice.hardware.abiList = AbiList(abiListString)
            tvDevice.software.apiLevel = "20-"
            tvDevice.software.liveWallpaperSupport = true
            tvDevice.software.glVersion = 2.0F
            tvDevice.software.statusBar = false
            return tvDevice
        }

        @JvmStatic
        fun templates(): List<Arguments> {
            val automotiveDevice = generateAutomotiveDevice()
            val tabletDevice = generateTabletDevice()
            val tvDevice = generateTVDevice()
            return listOf(
                Arguments.of("automotive-device.xml", automotiveDevice),
                Arguments.of("tablet-device.xml", tabletDevice),
                Arguments.of("tv-device.xml", tvDevice)
            )
        }
    }
}