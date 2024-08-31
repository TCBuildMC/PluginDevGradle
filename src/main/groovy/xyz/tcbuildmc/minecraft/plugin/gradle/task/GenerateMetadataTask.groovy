package xyz.tcbuildmc.minecraft.plugin.gradle.task

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import xyz.tcbuildmc.minecraft.plugin.gradle.PluginDevGradleExtension
import xyz.tcbuildmc.minecraft.plugin.gradle.metadata.generate.IGenerator

import java.nio.charset.StandardCharsets
import java.util.function.Predicate

abstract class GenerateMetadataTask extends DefaultTask {
    GenerateMetadataTask() {
        group = "pluginDevGradle"
        description = "Generates metadata for the plugin."
    }

    @Input
    String fileName = ""

    @Input
    Map<String, Object> metadata = new LinkedHashMap<>()

    @Input
    Predicate<Map<String, Object>> checker = (map) -> true

    @Input
    IGenerator generator = null

    @TaskAction
    void generate() {
        if (this.fileName.isEmpty()) {
            throw new GradleException("Failed to run generateMetadataTask.", new IllegalArgumentException("Empty fileName!"))
        }

        if (this.generator == null) {
            throw new GradleException("Failed to run generateMetadataTask.", new IllegalArgumentException("Empty generator!"))
        }

        if (!this.checker.test(this.metadata)) {
            throw new GradleException("Failed to run generateMetadataTask.", new IllegalArgumentException("Invalid metadata!"))
        }

        def s = this.generator.generator.apply(this.metadata)
        FileUtils.writeStringToFile(
                new File("src/${this.project.getExtensions().getByType(PluginDevGradleExtension).metadata.sourceSet}/resources",
                        this.fileName),
                s,
                StandardCharsets.UTF_8)
    }
}
