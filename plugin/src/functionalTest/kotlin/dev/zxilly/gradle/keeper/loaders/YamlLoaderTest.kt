package dev.zxilly.gradle.keeper.loaders

import kotlin.test.Test
import kotlin.test.assertEquals

class YamlLoaderTest {
    @Test fun `test load`() {
        val loader = YamlLoader(content)
        assertEquals(loader.load("json.0"), "rigid")
        assertEquals(loader.load("object.key"), "value")
        assertEquals(loader.load("object.array.0.null_value"), null)
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