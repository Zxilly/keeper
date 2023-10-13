package dev.zxilly.gradle.keeper.parsers

import kotlin.test.Test
import kotlin.test.assertEquals

class YamlParserTest {
    @Test fun `test load`() {
        val loader = YamlParser().apply {
            parse(content)
        }
        assertEquals(loader.get("json[0]"), "rigid")
        assertEquals(loader.get("object.key"), "value")
        assertEquals(loader.get("object.array[0].null_value"), null)
    }

    private val content = """
json:
- rigid
- better for data interchange
yaml:
- slim and flexible
- better for configuration
object:
  key: value
  array:
  - null_value: 
  - boolean: true
  - integer: 1
paragraph: |
  Blank lines denote
  paragraph breaks
    """.trimIndent()
}