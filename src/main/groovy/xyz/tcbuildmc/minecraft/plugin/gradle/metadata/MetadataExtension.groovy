package xyz.tcbuildmc.minecraft.plugin.gradle.metadata

import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit.BukkitMetadataExtension

class MetadataExtension {
    private final Project project

    MetadataExtension(Project project) {
        this.project = project
    }

    String sourceSet = SourceSet.MAIN_SOURCE_SET_NAME

    MetadataPlatform platform = MetadataPlatform.BUKKIT

    boolean check = true

    BukkitMetadataExtension bukkitMetadata = new BukkitMetadataExtension(this.project)
}
