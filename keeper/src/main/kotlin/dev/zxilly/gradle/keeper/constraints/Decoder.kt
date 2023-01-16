package dev.zxilly.gradle.keeper.constraints

interface Decoder {
    fun decode(value: String?): String?
}