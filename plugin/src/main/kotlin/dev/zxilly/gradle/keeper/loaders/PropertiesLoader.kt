package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.Loader
import java.util.Properties as JavaProperties

class PropertiesLoader(
    content: String
) : Loader {
    private var properties: JavaProperties = JavaProperties()

    init {
        properties.load(content.byteInputStream())
    }

    override fun load(key: String): String? {
        return this.properties.getProperty(key)
    }
}