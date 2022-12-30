@file:Suppress("UnstableApiUsage")

import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    val ktVersion = "1.8.0"

    `java-gradle-plugin`
    kotlin("jvm") version ktVersion
    kotlin("plugin.serialization") version ktVersion

    id("org.jetbrains.dokka") version "1.7.20"

    id("com.gradle.plugin-publish") version "1.1.0"
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

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:okhttp-brotli")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("com.charleskorn.kaml:kaml:0.49.0")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest("1.7.20")

            dependencies {
                implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
            }
        }

        val functionalTest by registering(JvmTestSuite::class) {
            useKotlinTest("1.7.20")

            dependencies {
                implementation(project())
                implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
                implementation("uk.org.webcompere:system-stubs-jupiter:2.0.1")
            }

            targets {
                all {
                    testTask.configure { shouldRunAfter(test) }
                }
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
            description = "A Gradle plugin to load secrets from different source build time."
            tags.set(listOf("secrets", "token", "environment"))
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