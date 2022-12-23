package dev.zxilly.gradle.keeper

import dev.zxilly.gradle.keeper.loaders.EnvironmentLoader
import dev.zxilly.gradle.keeper.loaders.JsonLoader
import dev.zxilly.gradle.keeper.loaders.PropertiesLoader
import org.gradle.api.Project
import java.io.File
import java.net.URL

open class ConfigExtension(private val project: Project) {
    // If true, the plugin will throw an exception if no value is found
    open var expectValue by GradleBooleanProperty(project, false)

    // Loaders to load the secret
    open val loaders = mutableListOf<Loader>()

    @JvmOverloads
    fun environment(nameMapping: Boolean = false) {
        loaders.add(EnvironmentLoader(nameMapping))
    }

    @JvmOverloads
    fun properties(file: File = project.file("local.properties")) {
        loaders.add(PropertiesLoader(file.readText()))
    }

    fun properties(content: String) {
        loaders.add(PropertiesLoader(content))
    }

    @JvmOverloads
    fun properties(url: URL, headers: Map<String, String> = emptyMap()) {
        val content = Utils.get(url.toString(), headers)
        loaders.add(PropertiesLoader(content))
    }

    fun json(content: String) {
        loaders.add(JsonLoader(content))
    }

    @JvmOverloads
    fun json(url: URL, headers: Map<String, String> = emptyMap()) {
        val content = Utils.get(url.toString(), headers)
        loaders.add(JsonLoader(content))
    }
}