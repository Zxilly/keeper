package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.constraints.Loader
import java.util.Properties as JavaProperties

/**
 * @param content The properties string.
 */
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