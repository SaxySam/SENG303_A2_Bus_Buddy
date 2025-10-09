package nz.ac.canterbury.seng303.busbuddy.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

object RoutePreferences {
    val SELECTED_ROUTE = stringPreferencesKey("selected_route")

    suspend fun saveRoute(context: Context, route: String) {
        context.dataStore.edit { prefs ->
            prefs[SELECTED_ROUTE] = route
        }
    }

    fun getRoute(context: Context): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[SELECTED_ROUTE]
        }
    }
}