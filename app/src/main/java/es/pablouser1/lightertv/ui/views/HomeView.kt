package es.pablouser1.lightertv.ui.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import es.pablouser1.lightertv.helpers.Apps
import es.pablouser1.lightertv.ui.widgets.AppsRow
import kotlinx.coroutines.flow.map


@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun HomeView(context: Context, dataStore: DataStore<Preferences>) {
    val categoriesKey = stringSetPreferencesKey("categories")
    val categoriesFlow = dataStore.data.map { prefs ->
        // TODO: Remove placeholders
        prefs[categoriesKey] ?: setOf("Tab 1", "Tab 2")
    }

    val categories by categoriesFlow.collectAsStateWithLifecycle(setOf())

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column {
        TabRow (
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
        ) {
            categories.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onFocus = {
                        selectedTabIndex = index
                    },
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        text = tab
                    )
                }
            }
        }

        if (categories.isNotEmpty()) {
            AppsRow(Apps.fromCategory(categories.elementAt(selectedTabIndex), context), context)
        }
    }
}