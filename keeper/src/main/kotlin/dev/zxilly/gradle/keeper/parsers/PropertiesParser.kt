package dev.zxilly.gradle.keeper.parsers

import java.util.Properties as JavaProperties

class PropertiesParser : Parser {
    private val instance = JavaProperties()

    override fun parse(content: String) {
        instance.load(content.byteInputStream())
    }

    override fun get(key: String): String? {
        return instance.getProperty(key)
    }
}