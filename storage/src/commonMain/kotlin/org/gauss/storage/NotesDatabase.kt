package org.gauss.storage

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.gauss.notes.NoteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.gauss.NotesDb
import org.gauss.storage.driver.DriverFactory

interface NotesDatabase {

    fun observeAllNotes(): Flow<List<NoteEntity>>

    fun observeNote(id: String): Flow<NoteEntity>

    suspend fun addNote(note: NoteEntity)

    suspend fun updateNote(id: String, text: String)

    suspend fun markDone(id: String, isDone: Long)

    suspend fun deleteNote(id: String)
}

class NotesDatabaseImpl(
    driverFactory: DriverFactory,
    private val dispatcher: CoroutineDispatcher
) : NotesDatabase {


    private val queries = NotesDb(driverFactory.createDriver()).notesQueries;

//    private val queries = flowOf(driver)
//        .map { NotesDb(it).notesQueries }
//        .shareIn(scope, SharingStarted.WhileSubscribed(), 1)

    override fun observeAllNotes(): Flow<List<NoteEntity>> {
        return queries.selectAll()
            .asFlow()
            .mapToList(dispatcher)
    }

    override fun observeNote(id: String): Flow<NoteEntity> {
        return queries.select(id.toString())
            .asFlow()
            .mapToOne(dispatcher)
    }

    override suspend fun addNote(note: NoteEntity) {
        withContext(dispatcher) {
            queries.add(
                note.id.toString(),
                note.createdAt.toString(),
                note.text,
                note.isDone,
            )
        }
    }

    override suspend fun updateNote(id: String, text: String) {
        withContext(dispatcher) {
            queries.updateNote(text, id)
        }
    }

    override suspend fun markDone(id: String, isDone: Long) {
        withContext(dispatcher) {
            queries.markDone(isDone, id)
        }
    }

    override suspend fun deleteNote(id: String) {
        withContext(dispatcher) {
            queries.delete(id)
        }
    }

}
