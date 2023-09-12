package com.app.core.network

import org.koin.dsl.module

val networkModule = module {
    single { provideKtorClient() }
    single { GifRepository(get()) }
}