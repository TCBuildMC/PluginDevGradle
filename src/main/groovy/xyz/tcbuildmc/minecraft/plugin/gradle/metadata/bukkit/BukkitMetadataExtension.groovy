package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import xyz.tcbuildmc.common.annotation.Required

class BukkitMetadataExtension {
    private final Project project

    BukkitMetadataExtension(Project project) {
        this.project = project
    }

    @Required
    String name = project.getExtensions().getByType(BasePluginExtension).archivesName.getOrElse("")

    // use with processResources
    @Required
    String version = '${version}'

    @Required
    String mainClass = ""

    String description = ""
    String website = ""
    ApiVersion apiVersion = ApiVersion.LEGACY
    boolean supportsFolia = false
    String author = ""
    List<String> authors = []
    List<String> contributors = []
    LoadOrder load = LoadOrder.POSTWORLD
    String prefix = ""

    BasePermission defaultPermission = BasePermission.OP
    NamedDomainObjectContainer<Permission> permissions = this.project.container(Permission)  // permissions.all {}
    def permissions(Action<? super NamedDomainObjectContainer<Permission>> action) {
        action.execute this.permissions
    }

    NamedDomainObjectContainer<Command> commands = this.project.container(Command)  // commands.all {}
    def commands(Action<? super NamedDomainObjectContainer<Command>> action) {
        action.execute this.commands
    }

    List<String> depend = []
    List<String> softDepend = []
    List<String> loadBefore = []

    Map<String, Object> toMap() {
        return [ "name": this.name,
                 "version": this.version,
                 "main": this.mainClass,
                 "description": this.description,
                 "website": this.website,
                 "api-version": this.apiVersion.toString(),
                 "folia-supported": this.supportsFolia,
                 "author": this.author,
                 "authors": this.authors,
                 "contributors": this.contributors,
                 "load": this.load.toString(),
                 "prefix": this.prefix,
                 "default-permission": this.defaultPermission.toString(),
                 "permissions": this.permissions.configureEach { it.toMap() },
                 "commands": this.commands.configureEach { it.toMap() },
                 "depend": this.depend,
                 "softdepend": this.softDepend,
                 "loadbefore": this.loadBefore ]
    }
}
