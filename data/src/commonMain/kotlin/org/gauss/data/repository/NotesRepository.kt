package org.gauss.data.repository

import com.benasher44.uuid.Uuid
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.datetime.*
import org.gauss.data.model.Note
import org.gauss.data.utils.updateItem

interface NotesRepository {

    val notes: Flow<List<Note>>

    fun get(uuid: Uuid) : Flow<Note?>

    suspend fun addNote(text: String)

    suspend fun deleteNote(id: Uuid)

    suspend fun completeNote(id: Uuid, completed : Boolean)

    suspend fun editNote(id: Uuid, text: String)
}

class NotesRepositoryImpl() : NotesRepository {

    private val notesStateFlow : MutableStateFlow<List<Note>> = MutableStateFlow(emptyList());

    override val notes: Flow<List<Note>>
        get() = notesStateFlow.asStateFlow()

    override fun get(id: Uuid) : Flow<Note?> {
        return notesStateFlow.map { notes -> notes.first { note -> note.id == id } }
    }

    override suspend fun addNote(text: String) {
        val notes = notesStateFlow.value.toMutableList()
        val now = Clock.System.now()
        val zone = TimeZone.currentSystemDefault()
        notes.add(Note(text = text, createdAt = now.toLocalDateTime(zone)))
        notesStateFlow.emit(notes)
    }

    override suspend fun deleteNote(id: Uuid) {
        val notes = notesStateFlow.value.toMutableList()
        notes.removeAll { it.id == id }
        notesStateFlow.emit(notes)
    }

    override suspend fun completeNote(id: Uuid, completed : Boolean) {
        val notes = notesStateFlow.value.toMutableList()
        notesStateFlow.emit(notes.updateItem( { it.id == id}, { it.copy(isDone = completed)}))
    }

    override suspend fun editNote(id: Uuid, text: String) {
        val notes = notesStateFlow.value.toMutableList()
        notesStateFlow.emit(notes.updateItem( { it.id == id}, { it.copy(text = text)}))
    }


}
