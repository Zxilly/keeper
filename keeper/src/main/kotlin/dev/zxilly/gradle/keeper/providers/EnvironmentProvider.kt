package dev.zxilly.gradle.keeper.providers

import java.util.*

class EnvironmentProvider(private val nameMapping: Boolean = true) : Provider() {
    override fun get(key: String): String? {
        if (nameMapping) {
            return System.getenv(nameMap(key))
        }
        return System.getenv(key)
    }

    private fun nameMap(key: String): String {
        return key.replace(".", "_").uppercase(Locale.getDefault())
    }
}