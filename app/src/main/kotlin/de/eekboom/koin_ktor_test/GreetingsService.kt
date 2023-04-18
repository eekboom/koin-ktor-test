package de.eekboom.koin_ktor_test

import org.koin.core.annotation.Single

// @Single
class GreetingsService {
    fun getMessage(): String = "Hej, hej!"
}