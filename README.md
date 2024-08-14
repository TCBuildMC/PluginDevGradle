[![](https://jitpack.io/v/TCBuildMC/PluginDevGradle.svg)](https://jitpack.io/#TCBuildMC/PluginDevGradle)

# PluginDevGradle
The newer/ refactored version of [CurtainGradle](https://github.com/TCBuildMC/CurtainGradle/).

## Highlights
- Designs for Minecraft plugin development (Absolutely it is).
- Built in support for Bukkit (and its fork) API, BungeeCord (and its fork) API and Velocity API.
- Helps developers spend less time configuring/ writing Gradle build scripts.
- Supports Java 8+ and Gradle 8+ at least.

## Usage
### Quick start
- `settings.gradle`: 
```gradle
pluginManagement {
    repositories {
        // Other repositories...
        maven {
            name = "Jitpack"
            url = "https://jitpack.io/
        }
    }
}
```

- `build.gradle`: 
```gradle
plugins {
    id "xyz.tcbuildmc.minecraft.plugin.gradle" version "{VERSION}"
}
```

## TODO
### Completed
- [x] Commonly used maven repositories
- [x] Commonly used dependencies/ libs
- [x] Automatically setup Java version
- [x] Kotlin/ Scala/ Groovy support

### High priority
- [ ] Run server
- [ ] Plugin metadata (description) validate/ setup

### Low priority
- [ ] Custom jar relocate and shade
- [ ] Paperweight integration
- [ ] Setup plugin **Internals**
- [ ] Gradle 7.x support
- [ ] and more...

## 
