plugins {
    id("services.common")
}

dependencies {
    api(kotlin("stdlib"))
    api("com.google.inject", "guice", Versions.GUICE)
    compileOnly("com.google.auto.value", "auto-value-annotations", Versions.AUTO_VALUE)
    kapt("com.google.auto.value", "auto-value", Versions.AUTO_VALUE)
}

license {
    header.set(project.rootProject.resources.text.fromFile("HEADER.txt"))
    newLine.set(false)
}
