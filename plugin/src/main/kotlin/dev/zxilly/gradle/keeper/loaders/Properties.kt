package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.Loader
import org.gradle.api.Project
import java.util.Properties as JavaProperties

class Properties(
    project: Project,
    path: String = "local.properties",
) : Loader {
    private var properties: JavaProperties = JavaProperties()

    init {
        val file = project.file(path)
        if (!file.exists()) {
            throw IllegalArgumentException("File $path does not exist.")
        }

        this.properties.load(file.inputStream())
    }

    override fun load(key: String): String? {
        return this.properties.getProperty(key)
    }
}