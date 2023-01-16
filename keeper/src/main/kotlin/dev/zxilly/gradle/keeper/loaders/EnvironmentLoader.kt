package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.constraints.Loader
import java.util.*

/**
 * @param nameMapping If true, the key will be mapped to `KEY_VALUE` from `key.value`.
 */
class EnvironmentLoader(private var nameMapping: Boolean = false) : Loader {
    override fun load(key: String): String? {
        if (nameMapping) {
            return System.getenv(nameMap(key))
        }
        return System.getenv(key)
    }

    private fun nameMap(key: String): String {
        return key.replace(".","_").uppercase(Locale.getDefault())
    }
}