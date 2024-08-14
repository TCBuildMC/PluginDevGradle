package xyz.tcbuildmc.minecraft.plugin.gradle.lang

class LanguageExtension {
    /*
     * See java.withSourcesJar() && java.withJavadocJar()
     */
    boolean buildSourcesJar = true
    boolean buildJavadocJar = false

    /*
     * For IDEA and Eclipse.
     * Automatically downloads the sources and javadoc jar.
     */
    boolean resolveSourcesAndJavadocDependencies = true

    /*
     * MC 1.17- uses Java 8 (mandatory)
     * MC 1.17.* uses Java 16+
     * MC 1.18~1.20.4 uses Java 17+
     * MC 1.20.5+ uses Java 21+
     *
     * But recommended to be 8.
     */
    int javaVersion = 8

    /*
     * For Kotlin
     * Work with kotlin-jvm-plugin.
     */
    String kotlinJvmTarget = "1.8"

    /*
     * For Groovy
     * Work with groovy plugin.
     * default (Can be capital) -> dependencies.localGroovy()
     */
    String groovyVersion = "default"

    /*
     * For Scala
     * Work with scala plugin.
     */
    String scalaVersion = "2.13.14"
}
