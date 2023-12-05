package di

import org.gauss.storage.di.driverModule
import org.koin.core.context.startKoin
import org.koin.core.qualifier.*
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import screens.details.*
import screens.list.*
import screens.utils.DateTimeFormatter
import screens.utils.TimeFormatterImpl
import org.gauss.data.di.dataModule
import org.gauss.storage.di.storageModule

fun initKoin() = initKoin {}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            driverModule(),
            storageModule(),
            dataModule(),
            utilModule(),
            screenModule()
        )
    }

fun utilModule() = module {

    single<DateTimeFormatter>(qualifier = named("time")) { TimeFormatterImpl() }

    single<NoteListMapper> { NotesListMapperImpl(get(qualifier = named("time"))) }

    single<NoteDetailsMapper> { NoteDetailsMapperImpl() }

}

fun screenModule() = module {

    factory { NoteListScreenModel(get(), get()) }

    factory { NoteDetailScreenModel(get(), get(), get()) }

    single {  }

}
