@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "KotlinConstantConditions")

package dev.zxilly.gradle.keeper

import dev.zxilly.gradle.keeper.constraints.Decoder
import dev.zxilly.gradle.keeper.decoders.Base64Decoder
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

    fun <T> get(key: String, type: Class<T>): T? {
        val value = get(key) ?: return null
        return type.cast(value)
    }

    fun get(key: String, vararg decoders: Decoder): String? {
        val value = get(key)
        var result = value
        for (decoder in decoders) {
            result = decoder.decode(result) ?: throw RuntimeException("Decode failed for key: $key")
        }
        return result
    }

    fun getBase64(key: String): String? {
        return get(key, Base64Decoder())
    }
}