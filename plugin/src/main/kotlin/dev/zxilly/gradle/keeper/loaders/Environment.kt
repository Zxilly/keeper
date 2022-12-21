package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.Loader
import java.util.*

class Environment(private var shouldMap: Boolean = false) : Loader {
    override fun load(key: String): String? {
        if (shouldMap) {
            return System.getenv(nameMap(key))
        }
        return System.getenv(key)
    }

    private fun nameMap(key: String): String {
        return key.replace(".","_").uppercase(Locale.getDefault())
    }
}