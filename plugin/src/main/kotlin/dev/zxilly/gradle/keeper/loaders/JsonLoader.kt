package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.Loader
import kotlinx.serialization.json.*
import kotlinx.serialization.json.Json as KotlinJson


class JsonLoader(content: String) : Loader {
    private val element = KotlinJson.parseToJsonElement(content)

    override fun load(key: String): String? {
        val path = key.dotSplit()
        var current = element
        for (p in path) {
            current = current.get(p)
        }
        return when (current) {
            is JsonPrimitive -> current.content
            is JsonNull -> null
            else -> current.toString()
        }
    }

    private fun JsonElement.get(key: String): JsonElement {
        return when (this) {
            is JsonObject -> this[key] ?: throw Exception("Key $key not found")
            is JsonArray -> {
                val index = key.toIntOrNull() ?: throw Exception("Key $key is not an index")
                this.getOrNull(index) ?: throw Exception("Index $index not found, max index is ${this.size - 1}")
            }

            is JsonPrimitive -> throw Exception("Primitive type has no key")
            is JsonNull -> throw Exception("Null type has no key")
            else -> throw Exception("Unknown type")
        }
    }

    private fun String.dotSplit(): List<String> {
        val result = mutableListOf<String>()
        var current = ""
        var inQuote = false
        for (c in this) {
            if (c == '"') {
                inQuote = !inQuote
            } else if (c == '.' && !inQuote) {
                result.add(current)
                current = ""
            } else {
                current += c
            }
        }
        result.add(current)
        return result
    }
}
