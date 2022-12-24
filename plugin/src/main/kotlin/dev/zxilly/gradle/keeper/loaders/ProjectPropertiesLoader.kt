package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.Loader
import org.gradle.api.Project

class ProjectPropertiesLoader(private val project: Project) : Loader {
    override fun load(key: String): String? {
        return project.properties[key] as String?
    }
}