package xyz.tcbuildmc.minecraft.plugin.gradle

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.plugins.scala.ScalaPlugin
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.ide.visualstudio.plugins.VisualStudioPlugin
import org.gradle.plugins.ide.eclipse.EclipsePlugin
import org.gradle.plugins.ide.eclipse.model.EclipseModel
import org.gradle.plugins.ide.idea.IdeaPlugin
import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapperKt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import xyz.tcbuildmc.minecraft.plugin.gradle.lang.LanguageExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.metadata.MetadataExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit.BukkitChecker
import xyz.tcbuildmc.minecraft.plugin.gradle.metadata.generate.YamlGenerator
import xyz.tcbuildmc.minecraft.plugin.gradle.task.GenerateMetadataTask

class PluginDevGradle implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.pluginManager.apply JavaLibraryPlugin

        def versionOptional = Optional.ofNullable(PluginDevGradle.class.package.implementationVersion)

        project.logger.lifecycle "PluginDevGradle: ${versionOptional.orElse("0.0.0+unknown.0")}"
        project.logger.lifecycle "by TCBuildMC"

        def extension = project.extensions.create "pluginDevGradle", PluginDevGradleExtension, project

        setupConfigurations(project.configurations)

        project.afterEvaluate {
            setupTasks(project.tasks, extension.metadata)
            setupLanguageSupport(extension.language, project)
        }
    }

    private void setupConfigurations(ConfigurationContainer configurations) {
        // For velocity
        configurations.register("implementationAP") {
            configurations.named(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME).get().extendsFrom it
            configurations.named(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME).get().extendsFrom it
        }

        // For lombok
        configurations.register("compileOnlyAP") {
            configurations.named(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME).get().extendsFrom it
            configurations.named(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME).get().extendsFrom it
        }

        configurations.register("testCompileOnlyAP") {
            configurations.named(JavaPlugin.TEST_COMPILE_ONLY_CONFIGURATION_NAME).get().extendsFrom it
            configurations.named(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME).get().extendsFrom it
        }
    }

    private void setupTasks(TaskContainer tasks, MetadataExtension extension) {
        tasks.register("generateBukkitMetadata", GenerateMetadataTask) {
            it.fileName = "plugin.yml"
            it.metadata = extension.bukkitMetadata.toMap()
            it.generator = YamlGenerator.INSTANCE

            if (extension.check) {
                it.checker = BukkitChecker.INSTANCE
            }
        }
    }

    private void setupLanguageSupport(LanguageExtension language, Project project) {
        project.tasks.withType(JavaCompile).configureEach { t ->
            t.options.encoding = "UTF-8"
            t.options.release.set language.javaVersion
        }

        project.extensions.configure(JavaPluginExtension) { e ->
            e.sourceCompatibility = JavaVersion.toVersion language.javaVersion
            e.targetCompatibility = JavaVersion.toVersion language.javaVersion

            if (language.buildSourcesJar) {
                e.withSourcesJar()
            }

            if (language.buildJavadocJar) {
                e.withJavadocJar()
            }
        }

        if (project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
            project.logger.warn "Kotlin JVM plugin detected."

            project.tasks.withType(KotlinCompile).configureEach { t ->
                t.kotlinOptions.jvmTarget = language.kotlinJvmTarget
            }

            project.dependencies.add "implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${KotlinPluginWrapperKt.getKotlinPluginVersion(project)}"
        }

        if (project.plugins.hasPlugin(GroovyPlugin)) {
            project.logger.warn "Groovy plugin detected."

            if (language.groovyVersion.toLowerCase(Locale.ROOT) == "default") {
                project.dependencies.add JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project.dependencies.localGroovy()
            } else {
                def majorGroovyVersion = Integer.parseInt(language.groovyVersion.split("\\.")[0])

                if (majorGroovyVersion < 4) {
                    project.dependencies.add JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.codehaus.groovy:groovy-all:${language.groovyVersion}"
                } else {
                    project.dependencies.add JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.apache.groovy:groovy-all:${language.groovyVersion}"
                }
            }
        }

        if (project.plugins.hasPlugin(ScalaPlugin)) {
            project.logger.warn "Scala plugin detected."

            def majorScalaVersion = Integer.parseInt(language.scalaVersion.split("\\.")[0])

            if (majorScalaVersion < 3) {
                project.dependencies.add JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.scala-lang:scala-library:${language.scalaVersion}"
                project.dependencies.add JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.scala-lang:scala-compiler:${language.scalaVersion}"
                project.dependencies.add JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.scala-lang:scala-reflect:${language.scalaVersion}"
            } else {
                project.dependencies.add JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, "org.scala-lang:scala3-library_3:${language.scalaVersion}"
            }
        }

        if (project.plugins.hasPlugin(IdeaPlugin)) {
            project.logger.warn "IDEA plugin detected."

            project.extensions.getByType(IdeaModel).module {
                excludeDirs.addAll(project.files(".gradle", "build", ".idea", "out", "run").files)
                downloadJavadoc = language.resolveSourcesAndJavadocDependencies
                downloadSources = language.resolveSourcesAndJavadocDependencies
//                inheritOutputDirs = true
            }
        }

        if (project.plugins.hasPlugin(EclipsePlugin)) {
            project.logger.warn "Eclipse plugin detected."

            project.extensions.getByType(EclipseModel).classpath {
                downloadJavadoc = language.resolveSourcesAndJavadocDependencies
                downloadSources = language.resolveSourcesAndJavadocDependencies
            }
        }

        if (project.plugins.hasPlugin(VisualStudioPlugin)) {
            project.logger.warn "Visual Studio (Code) plugin detected."
        }
    }
}
