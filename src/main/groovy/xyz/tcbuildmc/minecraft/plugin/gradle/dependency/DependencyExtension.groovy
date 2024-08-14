package xyz.tcbuildmc.minecraft.plugin.gradle.dependency

class DependencyExtension {
    /*
     * version format: {mc version}-R{server release version}
     */
    String bukkitApi(String version) {
        return "org.bukkit:bukkit:${version}-SNAPSHOT"
    }

    String spigotApi(String version) {
        return "org.spigotmc:spigot-api:${version}-SNAPSHOT"
    }

    String paperApi(String version) {
        return "io.papermc.paper:paper-api:${version}-SNAPSHOT"
    }

    String paperMojangApi(String version) {
        return "io.papermc.paper:paper-mojangapi:${version}-SNAPSHOT"
    }

    String purpurApi(String version) {
        return "org.purpurmc.purpur:purpur-api:${version}-SNAPSHOT"
    }

    String leavesApi(String version) {
        return "org.leavesmc.leaves:leaves-api:${version}-SNAPSHOT"
    }

    String leavesServer(String version) {
        return "org.leavesmc.leaves:leaves:${version}-SNAPSHOT"
    }

    String bungeeCordApi(String version) {
        return "net.md-5:bungeecord-api:${version}-SNAPSHOT"
    }

    String waterfallApi(String version) {
        return "io.github.waterfallmc:waterfall-api:${version}-SNAPSHOT"
    }

    String velocityApi(String version) {
        return "com.velocitypowered:velocity-api:${version}-SNAPSHOT"
    }

    String bukkitCommandApi(String version) {
        return "dev.jorel:commandapi-bukkit-core:${version}"
    }

    String vaultApi(String version) {
        return "com.github.MilkBowl:VaultAPI:${version}"
    }

    String placeholderapi(String version) {
        return "me.clip:placeholderapi:${version}"
    }

    String itemNBTApi(String version) {
        return "de.tr7zw:item-nbt-api-plugin:${version}"
    }

    String protocolLib(String version) {
        return "com.comphenix.protocol:ProtocolLib:${version}"
    }

    String lombok(String version) {
        return "org.projectlombok:lombok:${version}"
    }

    String jbAnnotations(String version) {
        return "org.jetbrains:annotations:${version}"
    }

    // TCBuildMC... (Wait for rewriting)
}
