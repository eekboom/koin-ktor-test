package de.eekboom.koin_ktor_test

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respondText
import io.ktor.server.routing.IgnoreTrailingSlash
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.ksp.generated.module

fun main() {
    embeddedServer(Netty, port = 4711, module = Application::main).start(wait = true)
}

fun Application.main() {
    val appModule = KoinAppModule().module
    install(Koin) {
        modules(
            appModule,
            module {
                singleOf(::GreetingsService)
            }
        )
    }
    install(IgnoreTrailingSlash)
    install(ContentNegotiation) {
        json()
    }

    routing {
        val greetingsService by inject<GreetingsService>()

        get("/") {
            call.respondText(greetingsService.getMessage())
        }
    }
}