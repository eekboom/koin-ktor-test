import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import java.util.Locale

plugins {
    kotlin("jvm")

    // Kotlin compiler plugin needed by Koin dependency injection framework
    id("com.google.devtools.ksp")

    application

    // Needed by kotlinx-serialization-json to preprocess @Serializable classes with a compiler plugin
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}
dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // ktor - kotlin web framework
    implementation("io.ktor:ktor-server-core:${project.ext["ktor_version"]}")
    implementation("io.ktor:ktor-server-netty:${project.ext["ktor_version"]}")
    implementation("io.ktor:ktor-server-status-pages:${project.ext["ktor_version"]}")
    implementation("io.ktor:ktor-server-default-headers:${project.ext["ktor_version"]}")
    implementation("io.ktor:ktor-server-content-negotiation:${project.ext["ktor_version"]}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${project.ext["ktor_version"]}")

    // Koin dependency injection
    implementation("io.insert-koin:koin-core:${project.ext["koinCore_version"]}")
    implementation("io.insert-koin:koin-ktor:${project.ext["koinCore_version"]}")
    implementation("io.insert-koin:koin-annotations:${project.ext["koinAnnotations_version"]}")
    ksp("io.insert-koin:koin-ksp-compiler:${project.ext["koinAnnotations_version"]}")
}

application {
    mainClass.set("de.eekboom.koin_ktor_test.MainKt")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}
