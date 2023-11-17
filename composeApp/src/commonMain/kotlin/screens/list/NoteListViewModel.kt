package screens.list

import com.benasher44.uuid.Uuid

data class NoteListViewModel(
    val id : Uuid,
    val text: String,
    val createdAt: String,
    val isDone: Boolean = false,
)
