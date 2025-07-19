package paul.vickers.built4life.settings

import com.russhwolf.settings.Settings

object AppSettings {
    private val settings: Settings = Settings()

    fun putString(key: String, value: String) {
        settings.putString(key, value)
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return settings.getString(key, defaultValue)
    }

    fun remove(key: String) {
        settings.remove(key)
    }

    fun update(key: String, value: String) {
        settings.remove(key)
        settings.putString(key, value)
    }
}