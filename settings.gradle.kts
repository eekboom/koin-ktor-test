pluginManagement {
    plugins {
        kotlin("jvm") version settings.extra["kotlin_version"] as String
        id("com.google.devtools.ksp") version settings.extra["ksp_version"] as String
        kotlin("plugin.serialization") version settings.extra["kotlin_version"] as String
    }
}

rootProject.name = "koin-ktor-test"

include(
    "app"
)
