plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

object PluginVersions {

    const val KOTLIN = "1.5.21"
    const val LICENSER = "0.6.1"
    const val INDRA = "2.0.6"
    const val DOKKA = "1.4.30"
}

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-gradle-plugin", PluginVersions.KOTLIN)
    implementation("gradle.plugin.org.cadixdev.gradle", "licenser", PluginVersions.LICENSER)
    implementation("net.kyori", "indra-common", PluginVersions.INDRA)
    implementation("org.jetbrains.dokka", "dokka-gradle-plugin", PluginVersions.DOKKA)
}
