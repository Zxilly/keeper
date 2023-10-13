@file:Suppress("KotlinConstantConditions")

package dev.zxilly.gradle.keeper

import dev.zxilly.gradle.keeper.constraints.Decoder
import org.gradle.api.Project

class Keeper(private val project: Project, private val target: ProviderID? = null) {
    private val extension
        get() = project.extensions.getByType(KeeperConfigExtension::class.java)

    private val providers
        get() = extension.providers

    // get a secret as string
    fun get(key: String): String? {
        if (providers.isEmpty()) {
            throw IllegalStateException("No loader found, please add a loader to the keeper extension")
        }

        if (target == null) {
            for (loader in providers.values) {
                val value = loader.get(key)
                if (value != null) {
                    return value
                }
            }

            if (extension.expectValue) {
                throw RuntimeException("No value found for key: $key")
            }
            return null
        } else {
            val loader = providers[target] ?: throw RuntimeException("No loader found for id: $target")
            val value = loader.get(key)
            if (extension.expectValue && value == null) {
                throw RuntimeException("No value found for key: $key")
            }
            return value
        }
    }

    // get with decoders, the order of decoders is meaningful as the result of the previous decoder will be the input of the next decoder
    fun get(key: String, vararg decoders: Decoder): String? {
        val value = get(key) ?: return null

        var result = value
        for (decoder in decoders) {
            result = decoder.decode(result)
        }
        return result
    }

    fun from(id: ProviderID): Keeper {
        if (providers[id] == null) {
            throw RuntimeException("No loader found for id: $id")
        }
        return Keeper(project, id)
    }
}