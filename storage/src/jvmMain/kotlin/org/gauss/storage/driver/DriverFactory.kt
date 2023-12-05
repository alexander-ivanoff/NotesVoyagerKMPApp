package org.gauss.storage.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.gauss.NotesDb
import java.io.File

actual class DriverFactory {

    actual fun createDriver(): SqlDriver {
        val databasePath = File(System.getProperty("java.io.tmpdir"), "test.db")
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
        NotesDb.Schema.create(driver)
        return driver
    }
}
