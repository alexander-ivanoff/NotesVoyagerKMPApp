package screens.list

import org.gauss.data.model.Note
import screens.utils.DateTimeFormatter

interface NoteListMapper : (Note) -> NoteListViewModel

class NotesListMapperImpl(private val timeFormatter: DateTimeFormatter) : NoteListMapper {

    override fun invoke(note: Note): NoteListViewModel {
        return NoteListViewModel(
            id = note.id,
            text = note.text,
            createdAt = timeFormatter.invoke(note.createdAt),
            isDone = note.isDone,
        )
    }

}
