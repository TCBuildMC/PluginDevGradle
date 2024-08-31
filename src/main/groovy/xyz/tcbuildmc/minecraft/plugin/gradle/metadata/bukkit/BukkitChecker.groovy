package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.bukkit

import java.util.function.Predicate

class BukkitChecker implements Predicate<Map<String ,Object>> {
    static final BukkitChecker INSTANCE = new BukkitChecker()

    @Override
    boolean test(Map<String, Object> map) {
        return !((String) map.getOrDefault("name", "")).isEmpty() ||
                !((String) map.getOrDefault("version", "")).isEmpty() ||
                !((String) map.getOrDefault("main", "")).isEmpty()
    }
}
