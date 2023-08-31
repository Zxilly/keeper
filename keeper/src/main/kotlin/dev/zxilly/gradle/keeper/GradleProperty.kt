@file:Suppress("KotlinConstantConditions", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package dev.zxilly.gradle.keeper

import org.gradle.api.Project
import org.gradle.api.provider.Property
import kotlin.reflect.KProperty


internal class GradleProperty<T, V>(
    project: Project,
    type: Class<V>,
    default: V? = null
) {
    private val property: Property<V> = project.objects.property(type).apply {
        set(default)
    }

    operator fun getValue(thisRef: T, property: KProperty<*>): V =
        this.property.get()

    operator fun setValue(thisRef: T, property: KProperty<*>, value: V) =
        this.property.set(value)
}

internal class GradleIntProperty<T>(
    project: Project,
    default: Int? = null
) {
    private val property = project.objects.property(Integer::class.java).apply {
        set(default as? Integer)
    }

    operator fun getValue(thisRef: T, property: KProperty<*>): Int =
        this.property.get().toInt()

    operator fun setValue(thisRef: T, property: KProperty<*>, value: Int) =
        this.property.set(value as? Integer)
}


internal class GradleStringProperty<T>(
    project: Project,
    default: String? = null
) {
    private val property = project.objects.property(java.lang.String::class.java).apply {
        set(default as? java.lang.String)
    }

    operator fun getValue(thisRef: T, property: KProperty<*>): String =
        this.property.get().toString()

    operator fun setValue(thisRef: T, property: KProperty<*>, value: String) =
        this.property.set(value as? java.lang.String)
}

internal class GradleBooleanProperty<T>(
    project: Project,
    default: Boolean? = null
) {
    private val property = project.objects.property(java.lang.Boolean::class.java).apply {
        set(default as? java.lang.Boolean)
    }

    operator fun getValue(thisRef: T, property: KProperty<*>): Boolean =
        this.property.get().booleanValue()

    operator fun setValue(thisRef: T, property: KProperty<*>, value: Boolean) =
        this.property.set(value as? java.lang.Boolean)
}

internal class GradleListProperty<T, V>(
    project: Project,
    type: Class<V>,
    default: List<V>? = null
) {
    private val property = project.objects.listProperty(type).apply {
        set(default)
    }

    operator fun getValue(thisRef: T, property: KProperty<*>): MutableList<V> =
        this.property.get()

    operator fun setValue(thisRef: T, property: KProperty<*>, value: MutableList<V>) =
        this.property.set(value)
}