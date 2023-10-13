package dev.zxilly.gradle.keeper.parsers

import com.fasterxml.jackson.databind.JsonNode
import com.nfeld.jsonpathkt.JsonPath
import com.nfeld.jsonpathkt.extension.read

class JsonParser : Parser {
    private lateinit var instance: JsonNode

    override fun parse(content: String) {
        JsonPath.parse(content)?.let {
            instance = it
        } ?: throw Exception("Failed to parse json")
    }

    override fun get(key: String): String? {
        return instance.read<String>(key)
    }

}