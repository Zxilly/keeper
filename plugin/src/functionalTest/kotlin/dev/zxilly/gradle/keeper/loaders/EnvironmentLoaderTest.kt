package dev.zxilly.gradle.keeper.loaders

import org.junit.jupiter.api.extension.ExtendWith
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables
import uk.org.webcompere.systemstubs.jupiter.SystemStub
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension
import kotlin.test.Test

@ExtendWith(SystemStubsExtension::class)
class EnvironmentLoaderTest {
    @SystemStub
    private val env = EnvironmentVariables()

    @Test
    fun `test load`() {
        env.set("test", "test")

        val loader = EnvironmentLoader()
        assert(loader.load("test") == "test")
    }

    @Test
    fun `test load with name map`() {
        env.set("TEST_OK", "test")

        val loader = EnvironmentLoader(true)
        assert(loader.load("test.ok") == "test")
    }
}