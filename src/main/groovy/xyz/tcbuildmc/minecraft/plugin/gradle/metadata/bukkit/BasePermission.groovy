package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit

enum BasePermission {
    TRUE,
    FALSE,
    OP,
    NOT_OP;

    @Override
    String toString() {
        switch (this) {
            case TRUE:
                return "true"
            case FALSE:
                return "false"
            case NOT_OP:
                return "not op"
            default:
                return "op"
        }
    }
}
