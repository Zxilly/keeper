package dev.zxilly.gradle.keeper.constraints

// Loader loads value from some source.
interface Loader {
    fun load(key: String): String?
}