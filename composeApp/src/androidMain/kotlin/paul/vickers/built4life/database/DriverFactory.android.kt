package paul.vickers.built4life.database

import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import paul.vickers.built4life.B4LApplication
import paul.vickers.built4life.B4LDatabase

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            B4LDatabase.Schema,
            B4LApplication.b4lApplication.applicationContext,
            "b4l.db",
            callback = object : AndroidSqliteDriver.Callback(B4LDatabase.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }
}