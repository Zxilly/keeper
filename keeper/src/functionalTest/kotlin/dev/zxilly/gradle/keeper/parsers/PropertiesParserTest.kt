package dev.zxilly.gradle.keeper.parsers

import kotlin.test.Test
import kotlin.test.assertEquals

class PropertiesParserTest {
    @Test
    fun `test load`() {
        val loader = PropertiesParser().apply {
            parse("test=test")
        }
        assertEquals("test", loader.get("test"))
    }
}