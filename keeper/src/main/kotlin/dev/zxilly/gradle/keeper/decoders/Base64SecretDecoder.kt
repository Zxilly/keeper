package dev.zxilly.gradle.keeper.decoders

import dev.zxilly.gradle.keeper.constraints.SecretDecoder
import java.util.*

class Base64SecretDecoder : SecretDecoder {
    override fun decode(value: String?): String? {
        if (value == null) {
            return null
        }
        return String(Base64.getDecoder().decode(value))
    }
}