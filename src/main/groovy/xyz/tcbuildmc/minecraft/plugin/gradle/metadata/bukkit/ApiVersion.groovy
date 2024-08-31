package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit

enum ApiVersion {
    LEGACY,
    V1_13,
    V1_14,
    V1_15,
    V1_16,
    V1_17,
    V1_18,
    V1_19,
    V1_20,
    V1_20_5,
    V_1_20_6,
    V1_21,
    V1_21_1;

    @Override
    String toString() {
        switch (this) {
            case LEGACY:
                return ""
            default:
                return this.name().substring(1).replaceAll("_", ".")
        }
    }
}