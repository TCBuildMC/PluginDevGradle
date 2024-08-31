package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit

enum LoadOrder {
    STARTUP,
    POSTWORLD;

    @Override
    String toString() {
        switch (this) {
            case STARTUP:
                return "STARTUP"
            default:
                return "POSTWORLD"
        }
    }
}