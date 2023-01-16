package dev.zxilly.gradle.keeper.decoders

import dev.zxilly.gradle.keeper.constraints.Decoder
import java.util.*

class Base64Decoder : Decoder {
    override fun decode(value: String?): String? {
        if (value == null) {
            return null
        }
        return String(Base64.getDecoder().decode(value))
    }
}