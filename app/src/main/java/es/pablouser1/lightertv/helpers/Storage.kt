package es.pablouser1.lightertv.helpers

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Storage(private val dataStore: DataStore<Preferences>) {
    fun getPreferences(): Flow<Set<String>> {
        val categoriesKey = stringSetPreferencesKey("categories")
        val categoriesFlow = dataStore.data.map { prefs ->
            // TODO: Remove placeholders
            prefs[categoriesKey] ?: setOf()
        }

        return categoriesFlow
    }
}