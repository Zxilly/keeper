package dev.zxilly.gradle.keeper

// Loader loads value from some source.
interface Loader {
    fun load(key: String): String?
}