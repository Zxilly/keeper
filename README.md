# keeper

[![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/dev.zxilly.gradle.keeper)](https://plugins.gradle.org/plugin/dev.zxilly.gradle.keeper)

Read secret from different source at build time.

## Installation

```gradle
plugins {
  id "dev.zxilly.gradle.keeper" version "0.0.1"
}
```

```kotlin
plugins {
    id("dev.zxilly.gradle.keeper")
}
```

Make sure have `gradlePluginPortal` in your repositories.

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        // etc.
    }
}
```

## Usage

Add a loader by

```kotlin
keeper {
    // If true, will throw exception when secret not found.
    expectValue = false
    
    environment()
    properties()
}
```

then use `secret.get` to fetch secret.

```kotlin
val key = secret.get("key")
```

Keeper will try to find the secret in the loaders in the order they are added.

## Loaders

### Environment

Load secret from environment variables.

```kotlin
keeper {
    environment()
}
```

### Properties

Load secret from properties file. By default, it will load `local.properties` in the root project directory.

```kotlin
keeper {
    properties()
}
```

#### Arguments

`path:File` - The path of the properties file, relative to the root project directory.

### Custom

You can also write your own loader by implementing `dev.zxilly.gradle.keeper.Loader` interface.

```kotlin
import dev.zxilly.gradle.keeper.Loader

class CustomLoader : Loader {
    override fun load(key: String): String? {
        // return the secret
        return null
    }
}
```

Then add it to keeper.

```kotlin
keeper {
    loaders += CustomLoader()
}
```

