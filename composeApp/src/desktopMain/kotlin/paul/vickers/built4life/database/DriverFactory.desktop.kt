package paul.vickers.built4life.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import paul.vickers.built4life.B4LDatabase
import java.util.Properties

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:test.db", Properties(), B4LDatabase.Schema)
        return driver
    }
}