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

Load secret from properties file or url. By default, it will load `local.properties` in the root project directory.

```kotlin
keeper {
    properties(File("local.properties"))
    properties("https://example.com/secret.properties")
}
```

A `projectProperties()` loader is also provided, which acts like `project.property` in gradle.

```kotlin
keeper {
    projectProperties()
}
```

### Json

Load json from a json file or url.

```kotlin
keeper {
    json(File("path/to/file.json"))
    json("https://example.com/file.json")
    json("https://example.com/file.json", mapOf("token" to "value")) // with headers
}
```

Use dot split to access the json. For json array, use index.
```kotlin
// {"a": {"b": "c"}}

val key = secret.get("a.b")
//  key = "c"
```

### Yaml

Load yaml from a yaml file or url.

```kotlin
keeper {
    yaml(File("path/to/file.yaml"))
    yaml("https://example.com/file.yaml")
    yaml("https://example.com/file.yaml", mapOf("token" to "value")) // with headers
}
```

Use dot split to access the yaml. For yaml array, use index.
```yaml
a:
  b: c
```

```kotlin
val key = secret.get("a.b")
//  key = "c"
```

### Xml

Load xml from a xml file or url.

```kotlin
keeper {
    xml(File("path/to/file.xml"))
    xml("https://example.com/file.xml")
    xml("https://example.com/file.xml", mapOf("token" to "value")) // with headers
}
```

Use xpath to access the xml.
```xml
<root>
    <element attr="value">text</element>
</root>
```

```kotlin
val key = secret.get("/root/element/@attr")
//  key = "value"
```

### Custom

You can also write your own loader by implementing `dev.zxilly.gradle.keeper.Loader` interface.

```kotlin
import dev.zxilly.gradle.keeper.constraints.Loader

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

