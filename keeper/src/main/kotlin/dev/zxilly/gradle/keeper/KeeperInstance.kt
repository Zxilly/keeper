@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "KotlinConstantConditions")

package dev.zxilly.gradle.keeper

import org.gradle.api.Project

class KeeperInstance(private val project: Project) {
    fun get(key: String): String? {
        val extension = project.extensions.getByType(KeeperConfigExtension::class.java)
        if (extension.loaders.isEmpty()) {
            throw IllegalStateException("No loader found, please add a loader to the keeper extension")
        }

        for (loader in extension.loaders) {
            val value = loader.load(key)
            if (value != null) {
                return value
            }
        }

        if (extension.expectValue) {
            throw RuntimeException("No value found for key: $key")
        }
        return null
    }
}