package org.gauss.storage.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.gauss.NotesDb

actual class DriverFactory {
  actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(NotesDb.Schema, "notes.db")
  }
}
