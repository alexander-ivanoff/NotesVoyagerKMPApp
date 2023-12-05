package screens.details

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.benasher44.uuid.Uuid
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gauss.data.repository.NotesRepository

class NoteDetailScreenModel(
    private val id: Uuid,
    private val repository: NotesRepository,
    private val viewModelMapper: NoteDetailsMapper,
) : StateScreenModel<NoteDetailScreenModel.State>(State(null)) {

    data class State(
        val note : NoteDetailViewModel?
    )

    init {
        screenModelScope.launch {
            repository.get(id)
                .collect { note -> mutableState.update { it.copy(note = viewModelMapper(note)) } }
        }
    }

    fun completeNote(completed: Boolean) {
        screenModelScope.launch {
            repository.markDone(id = id, completed = completed)
        }
    }

    fun editNote(text: String) {
        screenModelScope.launch {
            repository.editNote(id = id, text = text)
        }
    }

    override fun onDispose() {
        println("NoteListScreenModel: dispose")
    }


}

