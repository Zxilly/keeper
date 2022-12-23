package dev.zxilly.gradle.keeper.loaders

import kotlin.test.Test

class PropertiesLoaderTest {
    @Test
    fun `test load`() {
        val loader = PropertiesLoader("test=test")
        assert(loader.load("test") == "test")
    }
}