@file:Suppress("KotlinConstantConditions")

package dev.zxilly.gradle.keeper

import dev.zxilly.gradle.keeper.constraints.SecretDecoder
import dev.zxilly.gradle.keeper.decoders.Base64SecretDecoder
import org.gradle.api.Project

class KeeperInstance(private val project: Project) {
    // get a secret as string
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

    // get with type, the type must be a subclass of String
    fun <T> get(key: String, type: Class<T>): T? {
        val value = get(key) ?: return null
        return type.cast(value)
    }

    // get with decoders, the order of decoders is meaningful as the result of the previous decoder will be the input of the next decoder
    fun get(key: String, vararg secretDecoders: SecretDecoder): String? {
        val value = get(key) ?: return null

        var result = value
        for (decoder in secretDecoders) {
            result = decoder.decode(result) ?: throw RuntimeException("Decode failed for key: $key")
        }
        return result
    }

    // get with base64 decoder
    fun getBase64(key: String): String? {
        return get(key, Base64SecretDecoder())
    }
}