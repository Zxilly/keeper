package dev.zxilly.gradle.keeper

import org.gradle.api.Plugin
import org.gradle.api.Project

class KeeperPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("keeper", KeeperConfigExtension::class.java, project)

        project.extensions.add(Keeper::class.java, "secret", Keeper(project))
    }
}

