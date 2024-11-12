package es.pablouser1.lightertv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import es.pablouser1.lightertv.helpers.Apps
import es.pablouser1.lightertv.ui.theme.LighterTVTheme
import es.pablouser1.lightertv.ui.widgets.AppsRow


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Make async
        val apps = Apps.all(this)

        setContent {
            LighterTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    AppsRow(apps, this@MainActivity)
                }
            }
        }
    }
}
