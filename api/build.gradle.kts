plugins {
    id("services.common")
}

dependencies {
    api(kotlin("stdlib"))
    api("com.google.inject", "guice", Versions.GUICE)
}

license {
    header.set(project.rootProject.resources.text.fromFile("HEADER.txt"))
    newLine.set(false)
}
