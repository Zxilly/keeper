@file:Suppress("UnstableApiUsage")

import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    val ktVersion = "1.9.10"

    `java-gradle-plugin`
    kotlin("jvm") version ktVersion
    kotlin("plugin.serialization") version ktVersion

    id("org.jetbrains.dokka") version "1.9.0"

    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "dev.zxilly.gradle"
version = "dev"

if (System.getenv("CI") != null) {
    val type = System.getenv("GITHUB_EVENT_NAME")
    version = when (type) {
        "release" -> System.getenv("GITHUB_REF").split("/").last()
        else -> "snapshot"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:okhttp-brotli")

    implementation("com.nfeld.jsonpathkt:jsonpathkt:2.0.1")
    implementation("io.github.yaml-path:yaml-path:0.0.10")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

val ktTestVersion = "1.9.10"

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(ktTestVersion)
        }

        val functionalTest by registering(JvmTestSuite::class) {
            useKotlinTest(ktTestVersion)

            dependencies {
                implementation(project())
                implementation("uk.org.webcompere:system-stubs-jupiter:2.1.3")
            }

            targets {
                all {
                    testTask.configure { shouldRunAfter(test) }
                }
            }
        }
    }
}

kotlin {
    jvmToolchain(11)
    target {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
                languageVersion = "1.9"
            }
        }
    }
}

tasks.withType(DokkaTask::class.java).configureEach {
    moduleName.set("Keeper")
}

gradlePlugin {
    website.set("https://github.com/Zxilly/keeper")
    vcsUrl.set("https://github.com/Zxilly/keeper")

    plugins {
        create("keeper") {
            id = "dev.zxilly.gradle.keeper"
            implementationClass = "dev.zxilly.gradle.keeper.KeeperPlugin"
            displayName = "Keeper"
            description = "A Gradle plugin to load secret from different source at build time."
            tags.set(listOf("secret", "token", "environment"))
        }
    }
}

gradlePlugin.testSourceSets(sourceSets["functionalTest"])

tasks.named<Task>("check") {
    dependsOn(testing.suites.named("functionalTest"))
}

tasks.test {
    useJUnitPlatform()
}