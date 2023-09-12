package com.app.domain.use_case

import org.koin.dsl.module

val useCaseModule = module {
    single { FetchGifUC(get()) }
    single { GetPagerUC(get()) }
}