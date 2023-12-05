package org.gauss.storage.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.gauss.storage.NotesDatabase
import org.gauss.storage.NotesDatabaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun storageModule() = module {

    single<NotesDatabase> { NotesDatabaseImpl(get(), get()) }

    single<CoroutineDispatcher> { Dispatchers.Default }

}

expect fun driverModule(): Module
