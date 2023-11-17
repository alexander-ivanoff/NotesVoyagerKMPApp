package org.gauss.data
import org.gauss.data.repository.NotesRepository
import org.gauss.data.repository.NotesRepositoryImpl
import org.koin.dsl.module

fun dataModule() = module {

    single<NotesRepository> { NotesRepositoryImpl() }

}
