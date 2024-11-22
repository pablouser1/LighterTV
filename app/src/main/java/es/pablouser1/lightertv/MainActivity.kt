package es.pablouser1.lightertv

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import es.pablouser1.lightertv.helpers.Storage
import es.pablouser1.lightertv.ui.theme.LighterTVTheme
import es.pablouser1.lightertv.ui.views.HomeView

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = Storage(dataStore)

        setContent {
            LighterTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    HomeView(this@MainActivity, storage)
                }
            }
        }
    }
}
