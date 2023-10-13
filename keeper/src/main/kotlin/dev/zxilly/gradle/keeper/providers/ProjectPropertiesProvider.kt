package dev.zxilly.gradle.keeper.providers

import org.gradle.api.Project

class ProjectPropertiesProvider(private val project: Project) : Provider() {
    override fun get(key: String): String? {
        return project.properties[key] as String?
    }
}