plugins {
    id("services.common")
}

dependencies {
    api(project(":api"))
    api(project(":loader"))
    api("com.google.code.gson", "gson", Versions.GSON)
}
