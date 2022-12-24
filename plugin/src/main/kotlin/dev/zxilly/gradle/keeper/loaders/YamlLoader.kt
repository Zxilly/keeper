package dev.zxilly.gradle.keeper.loaders

import com.charleskorn.kaml.*
import dev.zxilly.gradle.keeper.Loader

class YamlLoader(content:String):Loader {

    private val element = Yaml.default.parseToYamlNode(content)

    override fun load(key: String): String? {
        val path = key.split('.')
        var current = element
        for (p in path) {
            current = current.get(p)
        }
        return when (current) {
            is YamlScalar -> current.content
            is YamlNull -> null
            else -> current.toString()
        }
    }
}

private fun YamlNode.get(p: String): YamlNode {
    return when (this) {
        is YamlMap -> this[p] ?: throw Exception("Key $p not found")
        is YamlList -> {
            val index = p.toIntOrNull() ?: throw Exception("Key $p is not an index")
            this.items.getOrNull(index) ?: throw Exception("Index $index not found, max index is ${this.items.size - 1}")
        }
        is YamlScalar -> throw Exception("Primitive type has no key")
        is YamlNull -> throw Exception("Null type has no key")
        else -> throw Exception("Unknown type")
    }
}
