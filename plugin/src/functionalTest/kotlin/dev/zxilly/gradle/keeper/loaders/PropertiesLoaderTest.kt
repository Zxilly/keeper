package dev.zxilly.gradle.keeper.loaders

import kotlin.test.Test
import kotlin.test.assertEquals

class PropertiesLoaderTest {
    @Test
    fun `test load`() {
        val loader = PropertiesLoader("test=test")
        assertEquals("test", loader.load("test"))
    }
}