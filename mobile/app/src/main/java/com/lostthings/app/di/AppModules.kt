package com.lostthings.app.di

import com.lostthings.data.repository.ItemRepository
import com.lostthings.data.service.ItemService
import com.lostthings.data.session.UserSession
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataModules = module {
    single { ItemService().itemApi }
    single { UserSession(androidApplication()) }
    single { ItemRepository(get(), get()) }
}

val appModules = listOf(dataModules)
