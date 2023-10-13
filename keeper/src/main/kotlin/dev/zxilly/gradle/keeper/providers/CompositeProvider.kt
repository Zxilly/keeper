package dev.zxilly.gradle.keeper.providers

import dev.zxilly.gradle.keeper.constraints.Decoder
import dev.zxilly.gradle.keeper.loaders.Loader
import dev.zxilly.gradle.keeper.parsers.Parser

class CompositeProvider(
    loader: Loader,
    private val parser: Parser,
    vararg decoders: Decoder
) : Provider() {
    init {
        val content = loader.load()
        parser.parse(content)
        this.decoders.addAll(decoders)
    }

    override fun get(key: String): String? {
        with(parser.get(key)) {
            if (this == null) return null
            return decoders.fold(this) { acc, decoder -> decoder.decode(acc) }
        }
    }
}