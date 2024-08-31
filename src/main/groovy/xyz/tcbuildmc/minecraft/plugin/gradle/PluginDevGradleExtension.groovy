package xyz.tcbuildmc.minecraft.plugin.gradle

import org.gradle.api.Action
import org.gradle.api.Project
import xyz.tcbuildmc.minecraft.plugin.gradle.dependency.DependencyExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.lang.LanguageExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.metadata.MetadataExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.repository.RepositoryExtension

class PluginDevGradleExtension {
    private final Project project

    PluginDevGradleExtension(Project project) {
        this.project = project
    }

    RepositoryExtension repository = new RepositoryExtension(this.project.repositories)
    LanguageExtension language = new LanguageExtension()
    DependencyExtension dependency = new DependencyExtension()
    MetadataExtension metadata = new MetadataExtension(this.project)

    def repositories(Action<? super RepositoryExtension> action) {
        action.execute this.repository
    }

    def language(Action<? super LanguageExtension> action) {
        action.execute this.language
    }

    def dependency(Action<? super DependencyExtension> action) {
        action.execute this.dependency
    }

    def metadata(Action<? super MetadataExtension> action) {
        action.execute this.metadata
    }
}
