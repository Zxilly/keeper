package dev.zxilly.gradle.keeper

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class KeeperPluginFunctionalTest {

    @field:TempDir
    lateinit var projectDir: File

    private val buildFile by lazy { projectDir.resolve("build.gradle") }
    private val settingsFile by lazy { projectDir.resolve("settings.gradle") }

    @Test fun `can run`() {
        // Set up the test build
        settingsFile.writeText("")
        buildFile.writeText("""
            plugins {
                id('dev.zxilly.gradle.keeper')
            }
            
            keeper {
                environment()
            }
            
            println secret.get("test")
        """.trimIndent())



        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withProjectDir(projectDir)
        runner.withEnvironment(mapOf(
            "test" to "ci_test"
        ))
        val result = runner.build()

        // Verify the result
        assertTrue(result.output.contains("ci_test"))
    }
}
