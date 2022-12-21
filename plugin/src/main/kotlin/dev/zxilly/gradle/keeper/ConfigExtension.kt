package dev.zxilly.gradle.keeper

import dev.zxilly.gradle.keeper.loaders.Environment
import dev.zxilly.gradle.keeper.loaders.Properties
import org.gradle.api.Project

open class ConfigExtension(private val project: Project) {
    // If true, the plugin will throw an exception if no value is found
    open var expectValue by GradleBooleanProperty(project, false)

    // Loaders to load the secret
    open val loaders = mutableListOf<Loader>()

    fun environment() {
        loaders.add(Environment())
    }

    fun environment(shouldMap: Boolean = false) {
        loaders.add(Environment(shouldMap))
    }

    fun properties() {
        loaders.add(Properties(project))
    }

    fun properties(path: String = "local.properties") {
        loaders.add(Properties(project, path))
    }
}