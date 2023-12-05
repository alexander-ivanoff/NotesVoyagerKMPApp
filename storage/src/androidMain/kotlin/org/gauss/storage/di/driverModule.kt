package org.gauss.storage.di

import org.gauss.storage.driver.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun driverModule(): Module = module {
    single { DriverFactory(get()) }
}
