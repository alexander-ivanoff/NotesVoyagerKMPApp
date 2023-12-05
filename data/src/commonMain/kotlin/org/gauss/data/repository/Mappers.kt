package org.gauss.data.repository

import com.benasher44.uuid.uuidFrom
import com.gauss.notes.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import org.gauss.data.model.Note

internal fun mapToDomain(entity: NoteEntity) = Note(
    id = uuidFrom(entity.id),
    createdAt = LocalDateTime.parse(entity.createdAt),
    text = entity.text,
    isDone = entity.isDone.asBoolean(),
)

internal fun mapToEntity(note: Note) = NoteEntity(
    id = note.id.toString(),
    createdAt = note.createdAt.toString(),
    text = note.text,
    isDone = note.isDone.asLong(),
)

internal fun <T, R> Flow<Iterable<T>>.mapIterable(mapper: (T) -> R): Flow<List<R>> = map { it.map(mapper) }


internal fun Boolean.asLong() = if (this) 1L else 0L

internal fun Long.asBoolean() = this != 0L
