package dev.zxilly.gradle.keeper.loaders

import java.io.File

class FileLoader : Loader {
    private val file: File

    constructor(path: String) {
        this.file = File(path)
        if (!this.file.isFile) throw IllegalArgumentException("File $path does not exist.")
        if (!this.file.canRead()) throw IllegalArgumentException("File $path cannot be read.")
    }

    constructor(file: File) {
        this.file = file
    }

    override fun load(): String {
        return file.readText()
    }
}