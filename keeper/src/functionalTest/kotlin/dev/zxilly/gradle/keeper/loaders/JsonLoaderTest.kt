package dev.zxilly.gradle.keeper.loaders

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonLoaderTest {
    @Test
    fun `test load`() {
        val loader = JsonLoader(content)
        assertEquals(loader.load("slideshow.author"), "Yours Truly")
        assertEquals(loader.load("slideshow.slides.0.title"), "Wake up to WonderWidgets!")
    }

    @Test
    fun `test load with name map`() {
        val loader = JsonLoader(content)
        assertEquals(loader.load("slideshow.\"da.te\""), "date of publication")
    }

    @Test
    fun `test load with failed key`() {
        val loader = JsonLoader(content)
        val exp = assertThrows<Exception> {
            loader.load("slideshow.author.name")
        }
        assertEquals("Primitive type has no key", exp.message)

        val exp2 = assertThrows<Exception> {
            loader.load("slideshow.slides.100.title")
        }
        assertEquals("Index 100 not found, max index is 1", exp2.message)

        val exp3 = assertThrows<Exception> {
            loader.load("slideshow.slides.notindex")
        }
        assertEquals("Key notindex is not an index", exp3.message)
    }


    private val content: String = """
        {
          "slideshow": {
            "author": "Yours Truly",
            "da.te": "date of publication",
            "slides": [
              {
                "title": "Wake up to WonderWidgets!",
                "type": "all"
              },
              {
                "items": [
                  "Why <em>WonderWidgets</em> are great",
                  "Who <em>buys</em> WonderWidgets"
                ],
                "title": "Overview",
                "type": "all"
              }
            ],
            "title": "Sample Slide Show"
          }
        }
    """.trimIndent()
}