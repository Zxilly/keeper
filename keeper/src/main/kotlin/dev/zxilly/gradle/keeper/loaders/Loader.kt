package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.parsers.Parser
import dev.zxilly.gradle.keeper.providers.CompositeProvider
import dev.zxilly.gradle.keeper.providers.Provider

interface Loader {
    fun load(): String

    operator fun plus(parser: Parser): Provider {
        return CompositeProvider(this, parser)
    }
}