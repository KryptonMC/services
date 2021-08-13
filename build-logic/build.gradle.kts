plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

object PluginVersions {

    const val KOTLIN = "1.5.21"
    const val LICENSER = "0.6.1"
}

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-gradle-plugin", PluginVersions.KOTLIN)
    implementation("gradle.plugin.org.cadixdev.gradle", "licenser", PluginVersions.LICENSER)
}
