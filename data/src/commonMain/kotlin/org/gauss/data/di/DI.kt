package org.gauss.data.di
import org.gauss.data.repository.NotesRepository
import org.gauss.data.repository.NotesRepositoryImpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

public fun dataModule() = module {

    single<NotesRepository> { NotesRepositoryImpl(get()) }

}

