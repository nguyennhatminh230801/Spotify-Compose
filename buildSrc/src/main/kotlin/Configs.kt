import org.gradle.api.JavaVersion

object Configs {
    const val MIN_SDK = 26
    const val TARGET_SDK = 34
    const val COMPILE_SDK = 34
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val APPLICATION_ID = "com.nguyennhatminh614.spotifycompose"
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.7"
    val SOURCE_COMPATIBILITY = JavaVersion.VERSION_1_8
    val TARGET_COMPATIBILITY = JavaVersion.VERSION_1_8
    const val JVM_TARGET = "1.8"
}