package org.gauss.data.repository

import com.benasher44.uuid.Uuid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.gauss.data.model.Note
import org.gauss.storage.NotesDatabase

class NotesRepositoryImpl(private val database : NotesDatabase) : NotesRepository {


    override val notes: Flow<List<Note>> = database
        .observeAllNotes()
        .mapIterable(::mapToDomain)

    override fun get(uuid: Uuid): Flow<Note?> = database
        .observeNote(uuid.toString())
        .map(::mapToDomain)

    override suspend fun addNote(text: String) {
        val now = Clock.System.now()
        val zone = TimeZone.currentSystemDefault()
        val note = Note(text = text, createdAt = now.toLocalDateTime(zone))
        database.addNote(mapToEntity(note))
    }

    override suspend fun deleteNote(id: Uuid) {
        database.deleteNote(id.toString())
    }

    override suspend fun markDone(id: Uuid, completed: Boolean) {
        database.markDone(id.toString(), completed.asLong())
    }

    override suspend fun editNote(id: Uuid, text: String) {
        database.updateNote(id.toString(), text)
    }
}
