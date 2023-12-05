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

    suspend fun markDone(id: Uuid, completed : Boolean)

    suspend fun editNote(id: Uuid, text: String)
}
