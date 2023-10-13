package dev.zxilly.gradle.keeper.parsers

import kotlin.test.Test
import kotlin.test.assertEquals

class JsonParserTest {
    private val content: String = """
        {
            "num": 23,
            "family": {
                "children": [{
                        "name": "Thomas",
                        "age": 13
                    },
                    {
                        "name": "Mila",
                        "age": 18
                    },
                    {
                        "name": "Konstantin",
                        "age": 29,
                        "nickname": "Kons"
                    },
                    {
                        "name": "Tracy",
                        "age": 4
                    }
                ]
            }
        }
    """.trimIndent()

    private val loader = JsonParser().apply {
        parse(content)
    }

    @Test
    fun `test load`() {
        assertEquals(loader.get("\$.family.children[2].name"), "Konstantin")
    }

    @Test
    fun `test load with failed key`() {
        assertEquals(loader.get("\$.notfound"), null)
        assertEquals(loader.get("\$.num"), "23")
    }
}