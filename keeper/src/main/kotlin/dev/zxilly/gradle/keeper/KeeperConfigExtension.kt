package dev.zxilly.gradle.keeper

import dev.zxilly.gradle.keeper.loaders.*
import dev.zxilly.gradle.keeper.parsers.JsonParser
import dev.zxilly.gradle.keeper.parsers.PropertiesParser
import dev.zxilly.gradle.keeper.parsers.XmlParser
import dev.zxilly.gradle.keeper.parsers.YamlParser
import dev.zxilly.gradle.keeper.providers.EnvironmentProvider
import dev.zxilly.gradle.keeper.providers.ProjectPropertiesProvider
import dev.zxilly.gradle.keeper.providers.Provider
import org.gradle.api.Project
import java.io.File
import java.net.URL

@Suppress("MemberVisibilityCanBePrivate")
open class KeeperConfigExtension(private val project: Project) {
    // If true, the plugin will throw an exception if no value is found
    var expectValue by GradleBooleanProperty(project, false)

    // Loaders to load the secret
    val providers = mutableMapOf<ProviderID, Provider>()

    fun attach(provider: Provider): ProviderID {
        providers[provider.id()] = provider
        return provider.id()
    }

    private fun guessLoader(str: String): Loader {
        return when {
            str.startsWith("http://") || str.startsWith("https://") -> NetLoader(str)
            else -> FileLoader(str)
        }
    }

    /**
     * @param nameMapping If true, the key will be mapped to `KEY_VALUE` from `key.value`.
     */
    @JvmOverloads
    fun environment(nameMapping: Boolean = false): ProviderID {
        return attach(EnvironmentProvider(nameMapping))
    }

    fun projectProperties(): ProviderID {
        return attach(ProjectPropertiesProvider(project))
    }

    /**
     * @param file The properties file.
     */
    @JvmOverloads
    fun properties(file: File = project.rootDir.resolve("local.properties")): ProviderID {
        return attach(FileLoader(file) + PropertiesParser())
    }

    /**
     * @param s The path of the properties file or an url.
     */
    fun properties(s: String): ProviderID {
        return attach(guessLoader(s) + PropertiesParser())
    }

    /**
     * @param url The url of the json file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun properties(url: URL, headers: Map<String, String> = emptyMap()): ProviderID {
        return attach(NetLoader(url.toString(), headers) + PropertiesParser())
    }

    /**
     * @param s The path to json file or an url.
     */
    fun json(s: String): ProviderID {
        return attach(guessLoader(s) + JsonParser())
    }

    /**
     * @param file The json file.
     */
    fun json(file: File): ProviderID {
        return attach(FileLoader(file) + JsonParser())
    }

    /**
     * @param url The url of the json file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun json(url: URL, headers: Map<String, String> = emptyMap()): ProviderID {
        return attach(NetLoader(url.toString(), headers) + JsonParser())
    }

    /**
     * @param file The file of the yaml file.
     */
    fun yaml(file: File): ProviderID {
        return attach(FileLoader(file) + YamlParser())
    }

    /**
     * @param s The path of the yaml file or an url.
     */

    fun yaml(s: String): ProviderID {
        return attach(guessLoader(s) + YamlParser())
    }

    /**
     * @param url The url of the yaml file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun yaml(url: URL, headers: Map<String, String> = emptyMap()): ProviderID {
        return attach(NetLoader(url.toString(), headers) + YamlParser())
    }

    /**
     * @param file The file of the xml file.
     */
    fun xml(file: File): ProviderID {
        return attach(FileLoader(file) + XmlParser())
    }

    /**
     * @param s The path of the xml file or an url.
     */
    fun xml(s: String): ProviderID {
        return attach(guessLoader(s) + XmlParser())
    }

    /**
     * @param url The url of the xml file.
     * @param headers The headers of the request.
     */
    @JvmOverloads
    fun xml(url: URL, headers: Map<String, String> = emptyMap()): ProviderID {
        return attach(NetLoader(url.toString(), headers) + XmlParser())
    }
}