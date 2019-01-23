package com.lostthings.app.di

import com.lostthings.data.repository.ItemRepository
import com.lostthings.data.service.ItemService
import org.koin.dsl.module.module

val dataModules = module {
    single { ItemService().itemApi }
    single { ItemRepository(get()) }
}

val appModules = listOf(dataModules)
