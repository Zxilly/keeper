package dev.zxilly.gradle.keeper

import dev.zxilly.gradle.keeper.constraints.Loader
import dev.zxilly.gradle.keeper.loaders.*
import org.gradle.api.Project
import java.io.File
import java.net.URL

open class KeeperConfigExtension(private val project: Project) {
    // If true, the plugin will throw an exception if no value is found
    open var expectValue by GradleBooleanProperty(project, false)

    // Loaders to load the secret
    open val loaders = mutableListOf<Loader>()

    /**
     * @param nameMapping If true, the key will be mapped to `KEY_VALUE` from `key.value`.
     */
    @JvmOverloads
    fun environment(nameMapping: Boolean = false) {
        loaders.add(EnvironmentLoader(nameMapping))
    }

    fun projectProperties() {
        loaders.add(ProjectPropertiesLoader(project))
    }

    /**
     * @param file The properties file.
     */
    @JvmOverloads
    fun properties(file: File = project.rootDir.resolve("local.properties")) {
        loaders.add(PropertiesLoader(file.readText()))
    }

    /**
     * @param content The properties string.
     */
    fun properties(content: String) {
        loaders.add(PropertiesLoader(content))
    }

    /**
     * @param url The url of the json file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun properties(url: URL, headers: Map<String, String> = emptyMap()) {
        val content = Utils.get(url.toString(), headers)
        loaders.add(PropertiesLoader(content))
    }

    /**
     * @param content The content of the json file.
     */
    fun json(content: String) {
        loaders.add(JsonLoader(content))
    }

    /**
     * @param url The url of the json file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun json(url: URL, headers: Map<String, String> = emptyMap()) {
        val content = Utils.get(url.toString(), headers)
        loaders.add(JsonLoader(content))
    }

    /**
     * @param file The file of the yaml file.
     */
    fun yaml(file: File) {
        loaders.add(YamlLoader(file.readText()))
    }

    /**
     * @param content The content of the yaml file.
     */

    fun yaml(content: String) {
        loaders.add(YamlLoader(content))
    }

    /**
     * @param url The url of the yaml file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun yaml(url: URL, headers: Map<String, String> = emptyMap()) {
        val content = Utils.get(url.toString(), headers)
        loaders.add(YamlLoader(content))
    }

    /**
     * @param file The file of the xml file.
     */
    fun xml(file: File) {
        loaders.add(XmlLoader(file.readText()))
    }

    /**
     * @param content The content of the xml file.
     */
    fun xml(content: String) {
        loaders.add(XmlLoader(content))
    }

    /**
     * @param url The url of the xml file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun xml(url: URL, headers: Map<String, String> = emptyMap()) {
        val content = Utils.get(url.toString(), headers)
        loaders.add(XmlLoader(content))
    }
}