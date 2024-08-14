package xyz.tcbuildmc.minecraft.plugin.gradle

import org.gradle.api.Action
import org.gradle.api.Project
import xyz.tcbuildmc.minecraft.plugin.gradle.dependency.DependencyExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.lang.LanguageExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.repository.RepositoryExtension

class PluginDevGradleExtension {
    private final Project project

    PluginDevGradleExtension(Project project) {
        this.project = project
    }

    RepositoryExtension repository = new RepositoryExtension(project.repositories)
    LanguageExtension language = new LanguageExtension()
    DependencyExtension dependency = new DependencyExtension()

    def repositories(Action<? super RepositoryExtension> action) {
        action.execute repository
    }

    def language(Action<? super LanguageExtension> action) {
        action.execute language
    }

    def dependency(Action<? super DependencyExtension> action) {
        action.execute dependency
    }
}
