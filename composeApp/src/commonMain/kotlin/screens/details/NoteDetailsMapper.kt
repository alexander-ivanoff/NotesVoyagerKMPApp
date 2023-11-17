package screens.details

import org.gauss.data.model.Note

interface NoteDetailsMapper : (Note?) -> NoteDetailViewModel?

class NoteDetailsMapperImpl : NoteDetailsMapper {

    override fun invoke(note: Note?): NoteDetailViewModel? {
        return note?.let {
            NoteDetailViewModel(text = it.text, isDone = it.isDone,)
        }
    }

}
