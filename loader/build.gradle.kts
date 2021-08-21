plugins {
    id("services.common")
}

repositories {
    maven("https://repo.kryptonmc.org/releases")
}

dependencies {
    api(project(":api"))
    api("org.jetbrains", "annotations", "20.1.0")
    compileOnly("com.google.auto.value", "auto-value-annotations", Versions.AUTO_VALUE)
    kapt("com.google.auto.value", "auto-value", Versions.AUTO_VALUE)

    testImplementation(kotlin("test-junit5"))
}

tasks.test {
    useJUnitPlatform()
}
