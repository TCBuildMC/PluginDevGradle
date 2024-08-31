package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.generate

import java.util.function.Function

@FunctionalInterface
interface IGenerator {
    Function<Map<String, Object>, String> getGenerator()
}