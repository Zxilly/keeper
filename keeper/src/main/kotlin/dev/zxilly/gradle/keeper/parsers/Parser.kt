package dev.zxilly.gradle.keeper.parsers

interface Parser {
    fun parse(content: String)

    fun get(key: String): String?
}