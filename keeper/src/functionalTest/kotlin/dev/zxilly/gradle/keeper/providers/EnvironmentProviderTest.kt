package dev.zxilly.gradle.keeper.providers

import org.junit.jupiter.api.extension.ExtendWith
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables
import uk.org.webcompere.systemstubs.jupiter.SystemStub
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(SystemStubsExtension::class)
class EnvironmentProviderTest {
    @SystemStub
    private val env = EnvironmentVariables()

    @Test
    fun `test load`() {
        env.set("test", "test")

        val loader = EnvironmentProvider(false)
        assertEquals("test", loader.get("test"))
    }

    @Test
    fun `test load with name map`() {
        env.set("TEST_OK", "test")

        val loader = EnvironmentProvider(true)
        assertEquals(loader.get("test.ok"), "test")
    }
}