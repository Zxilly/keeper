

plugins {
    id("dev.zxilly.gradle.keeper")
}

keeper {
    expectValue = false
    environment()
    properties()
}

println(secret.get("test"))