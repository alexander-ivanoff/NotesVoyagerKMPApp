package org.gauss.data.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Uuid = uuid4(),
    val createdAt: LocalDateTime,
    val text: String,
    val isDone: Boolean = false
)
