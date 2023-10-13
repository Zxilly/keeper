package dev.zxilly.gradle.keeper.decoders

import dev.zxilly.gradle.keeper.constraints.Decoder
import java.util.*

class Base64Decoder : Decoder {
    private val decoder = Base64.getDecoder()
    override fun decode(value: String): String {
        return String(decoder.decode(value))
    }
}