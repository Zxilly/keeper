package dev.zxilly.gradle.keeper.parsers

import io.github.yamlpath.YamlExpressionParser
import io.github.yamlpath.YamlPath

class YamlParser : Parser {
    private lateinit var instance: YamlExpressionParser

    override fun parse(content: String) {
        instance = YamlPath.from(content)
    }

    override fun get(key: String): String? {
        return instance.readSingle<String>(key)
    }
}