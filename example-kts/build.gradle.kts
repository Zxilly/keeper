

plugins {
    id("dev.zxilly.gradle.keeper")
}

keeper {
    expectValue = false
    environment()
}

println(secret.get("test"))