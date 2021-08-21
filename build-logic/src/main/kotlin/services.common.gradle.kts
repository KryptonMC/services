import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("org.cadixdev.licenser")
    id("org.jetbrains.dokka")
    `java-library`
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
    jcenter()
}

task<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

task<Jar>("javadocJar") {
    from(tasks.dokkaJavadoc)
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven("https://repo.kryptonmc.org/releases") {
            credentials {
                username = project.property("maven.username")!!.toString()
                password = project.property("maven.password")!!.toString()
            }
        }
    }
    publications {
        create<MavenPublication>("mavenKotlin") {
            artifactId = "services-${project.name}"

            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("Services")
                description.set("A simple yet powerful services system, inspired by Spring beans.")
                url.set("https://www.kryptonmc.org")
                inceptionYear.set("2021")
                packaging = "jar"

                developers {
                    developer {
                        id.set("bombardygamer")
                        name.set("Callum Seabrook")
                        email.set("callum.seabrook@prevarinite.com")
                        url.set("https://bardy.me")
                        organization.set("KryptonMC")
                        organizationUrl.set("https://github.com/KryptonMC")
                        roles.set(setOf("Developer", "Maintainer"))
                        timezone.set("Europe/London")
                    }
                }

                organization {
                    name.set("KryptonMC")
                    url.set("https://github.com/KryptonMC")
                }

                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/KryptonMC/services/issues")
                }

                scm {
                    connection.set("scm:git:git://github.com/KryptonMC/services.git")
                    developerConnection.set("scm:git:ssh://github.com:KryptonMC/services.git")
                    url.set("https://github.com/KryptonMC/services")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenKotlin"])
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

val compiler: Provider<JavaCompiler> = javaToolchains.compilerFor {
    languageVersion.set(JavaLanguageVersion.of(11))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        jdkHome = compiler.get().metadata.installationPath.asFile.absolutePath
    }
}
