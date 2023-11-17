package screens
import org.koin.core.qualifier.*
import org.koin.dsl.module
import screens.details.*
import screens.list.*
import screens.utils.DateTimeFormatter
import screens.utils.TimeFormatterImpl

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
