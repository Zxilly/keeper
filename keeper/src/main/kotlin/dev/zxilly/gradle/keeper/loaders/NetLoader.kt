package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.Utils

class NetLoader(private val url: String, private val headers: Map<String, String>) : Loader {
    constructor(url: String) : this(url, emptyMap())

    override fun load(): String {
        return Utils.get(url, headers)
    }
}