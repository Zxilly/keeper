@file:Suppress("UnstableApiUsage")

plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"
    id("com.gradle.plugin-publish") version "1.1.0"
}

group = "dev.zxilly.gradle"
version = "0.0.1"

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
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest("1.7.22")

            dependencies {
                implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
            }
        }

        val functionalTest by registering(JvmTestSuite::class) {
            useKotlinTest("1.7.22")

            dependencies {
                implementation(project())
                implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
                implementation("org.junit-pioneer:junit-pioneer:2.0.0-RC1")
            }

            targets {
                all {
                    testTask.configure { shouldRunAfter(test) }
                }
            }
        }
    }
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