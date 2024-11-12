package es.pablouser1.lightertv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.material3.CardLayoutDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.StandardCardLayout
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import es.pablouser1.lightertv.helpers.Apps
import es.pablouser1.lightertv.ui.theme.LighterTVTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apps = Apps.all(this)

        setContent {
            LighterTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    LazyRow(
                        modifier = Modifier.padding(30.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(apps.size) {i ->
                            val app = apps[i]
                            val icon = app.loadIcon(packageManager)
                            val name = app.activityInfo.packageName

                            StandardCardLayout(
                                modifier = Modifier.size(180.dp, 160.dp),
                                imageCard = { interactionSource ->
                                    CardLayoutDefaults.ImageCard(
                                        onClick = {
                                            Apps.launch(name, this@MainActivity)
                                        },
                                        interactionSource = interactionSource
                                    ) {
                                        Image(
                                            painter = BitmapPainter(icon.toBitmap().asImageBitmap()),
                                            contentDescription = "Icon for $name app",
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                },
                                title = {
                                    Text(app.loadLabel(packageManager).toString())
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
