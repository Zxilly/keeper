package dev.zxilly.gradle.keeper.providers

import dev.zxilly.gradle.keeper.ProviderID
import dev.zxilly.gradle.keeper.constraints.Decoder
import java.util.UUID

abstract class Provider {
    protected val decoders: MutableList<Decoder> = mutableListOf()

    operator fun plus(decoder: Decoder): Provider {
        decoders.add(decoder)
        return this
    }

    abstract fun get(key: String): String?

    fun id(): ProviderID {
        return UUID.randomUUID().toString()
    }
}