package dev.zxilly.gradle.keeper.constraints

interface SecretDecoder {
    fun decode(value: String?): String?
}