package dev.zxilly.gradle.keeper.loaders

import org.junitpioneer.jupiter.ClearEnvironmentVariable.ClearEnvironmentVariables
import org.junitpioneer.jupiter.SetEnvironmentVariable
import kotlin.test.Test

class EnvironmentLoaderTest {
    @Test
    @ClearEnvironmentVariables
    @SetEnvironmentVariable(key = "test", value = "test")
    fun `test load`() {
        val loader = EnvironmentLoader()
        assert(loader.load("test") == "test")
    }

    @Test
    @ClearEnvironmentVariables
    @SetEnvironmentVariable(key = "TEST_OK", value = "test")
    fun `test load with name map`() {
        val loader = EnvironmentLoader(true)
        assert(loader.load("test.ok") == "test")
    }
}