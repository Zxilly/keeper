package dev.zxilly.gradle.keeper

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class KeeperPluginTest {
    @Test fun `plugin register`() {
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("dev.zxilly.gradle.keeper")

        assertNotNull(project.plugins.getPlugin(KeeperPlugin::class.java))

        val keeper = project.extensions.getByType(ConfigExtension::class.java)
        keeper.environment()

        val instance = project.extensions.getByType(KeeperInstance::class.java)
        assertNull(instance.get("test"))
    }
}
