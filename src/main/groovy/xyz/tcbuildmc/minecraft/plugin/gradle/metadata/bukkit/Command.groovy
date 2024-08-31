package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit

class Command {
    final String name

    String description = ""
    String usage = ""
    List<String> aliases = []
    String permission = ""
    String permissionMessage = ""

    Command(String name) {
        this.name = name
    }

    Map<String, Object> toMap() {
        return [ "name": this.name,
                 "description": this.description,
                 "usage": this.usage,
                 "aliases": this.aliases,
                 "permission": this.permission,
                 "permission-message": this.permissionMessage ]
    }
}
