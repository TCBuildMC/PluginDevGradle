package xyz.tcbuildmc.minecraft.plugin.gradle.repository

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

class RepositoryExtension {
    private final RepositoryHandler repositories

    RepositoryExtension(RepositoryHandler repositories) {
        this.repositories = repositories
    }

    MavenArtifactRepository spigotmc() {
        return repositories.maven {
            url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
        }
    }

    MavenArtifactRepository papermc() {
        return repositories.maven {
            url = "https://repo.papermc.io/repository/maven-public/"
        }
    }

    MavenArtifactRepository purpurmc() {
        return repositories.maven {
            url = "https://repo.purpurmc.org/snapshots/"
        }
    }

    MavenArtifactRepository leavesmc() {
        return repositories.maven {
            url = "https://repo.leavesmc.org/snapshots/"
        }
    }

    MavenArtifactRepository sonatype() {
        return repositories.maven {
            url = "https://oss.sonatype.org/content/repositories/snapshots/"
        }
    }

    MavenArtifactRepository jitpack() {
        return repositories.maven {
            url = "https://jitpack.io/"

            // Small optimization :(
            content {
                includeGroupAndSubgroups "com.github"
            }
        }
    }

    MavenArtifactRepository codemc() {
        return repositories.maven {
            url = "https://repo.codemc.org/repository/maven-public/"
        }
    }

    MavenArtifactRepository placeholderapi() {
        return repositories.maven {
            url = "https://repo.extendedclip.com/content/repositories/placeholderapi/"

            // Small optimization :(
            content {
                includeGroup "me.clip"
            }
        }
    }

    // Probably useful
    MavenArtifactRepository modrinth() {
        return repositories.maven {
            url = "https://api.modrinth.com/maven/"

            // Small optimization :(
            content {
                includeGroup "maven.modrinth"
            }
        }
    }

    MavenArtifactRepository protocolLib() {
        return repositories.maven {
            url = "https://repo.dmulloy2.net/repository/public/"
        }
    }
}
