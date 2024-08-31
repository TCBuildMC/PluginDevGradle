package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit

class Permission {
    final String name

    String description = ""
    BasePermission defaultPermission = BasePermission.OP
    Map<String, Boolean> children = [:]

    Permission(String name) {
        this.name = name
    }

    Map<String, Object> toMap() {
        return [ "name": this.name,
                 "description": this.description,
                 "default": this.defaultPermission.toString(),
                 "children": this.children ]
    }
}
