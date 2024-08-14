[![](https://jitpack.io/v/TCBuildMC/PluginDevGradle.svg)](https://jitpack.io/#TCBuildMC/PluginDevGradle)

[English](./README.md) | 中文

# PluginDevGradle
[CurtainGradle](https://github.com/TCBuildMC/CurtainGradle/) 的重构版

## 特点
- 为了 MC 插件开发设计（dddd）
- 提供  及其下游，BungeeCord 及其下游，Velocity API 支持
- 让插件开发者不用花太多时间在 Gradle 构建脚本上
- 支持 Java 8+ 和 Gradle 8+

## 用法
### 快速开始
- `settings.gradle`:
```gradle
pluginManagement {
    repositories {
        // Other repositories...
        maven {
            name = "Jitpack"
            url = "https://jitpack.io/"
        }
    }
}

resolutionStrategy {
    eachPlugin {
        switch (requested.id.id) {
            case "xyz.tcbuildmc.minecraft.plugin.gradle": {
                useModule "com.github.TCBuildMC.PluginDevGradle:PluginDevGradle:${requested.version}"
                break
            }
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
### 已完成
- [x] Commonly used maven repositories
- [x] Commonly used dependencies/ libs
- [x] Automatically setup Java version
- [x] Kotlin/ Scala/ Groovy support

### 高优先级
- [ ] Run server
- [ ] Plugin metadata (description) validate/ setup

### 低优先级
- [ ] Custom jar relocate and shade
- [ ] Paperweight integration
- [ ] Setup plugin **Internals**
- [ ] Gradle 7.x support
- [ ] and more...
