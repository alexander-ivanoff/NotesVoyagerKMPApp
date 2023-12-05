package screens.list

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.benasher44.uuid.Uuid
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.gauss.data.repository.NotesRepository
import org.gauss.data.utils.EMPTY

class NoteListScreenModel(
    private val repository: NotesRepository,
    private val viewModelMapper: NoteListMapper,
) : StateScreenModel<NoteListScreenModel.State>(State(emptyList())) {

    data class State(
        val items : List<NoteListViewModel> = emptyList(),
        val input : String = String.EMPTY,
    )

    init {
        screenModelScope.launch {
            repository.notes
                .collect { notes -> mutableState.update { it.copy(items = notes.map(viewModelMapper)) } }
        }
    }

    fun addNote() {
        screenModelScope.launch {
            repository.addNote(mutableState.value.input)
            mutableState.update { it.copy(input = String.EMPTY) }
        }
    }

    fun deleteNote(id: Uuid) {
        screenModelScope.launch {
            repository.deleteNote(id = id)
        }
    }

    fun changeInput(string: String) {
        screenModelScope.launch {
            mutableState.update { it.copy(input = string) }
        }
    }

    fun completeNote(id: Uuid, completed: Boolean) {
        screenModelScope.launch {
            repository.markDone(id = id, completed = completed)
        }
    }

    override fun onDispose() {
        println("NoteListScreenModel: dispose")

    }

}

