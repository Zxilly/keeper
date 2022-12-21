package dev.zxilly.gradle.keeper

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class KeeperPluginTest {
    @Test fun `plugin register`() {
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("dev.zxilly.gradle.keeper")

        assertNotNull(project.plugins.getPlugin(KeeperPlugin::class.java))
    }
}
