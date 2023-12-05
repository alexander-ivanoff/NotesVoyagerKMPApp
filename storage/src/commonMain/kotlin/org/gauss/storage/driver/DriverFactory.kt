package org.gauss.storage.driver

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
  fun createDriver(): SqlDriver
}
