package es.pablouser1.lightertv

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import es.pablouser1.lightertv.ui.theme.LighterTVTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        val apps: List<ResolveInfo> =
            packageManager.queryIntentActivities(intent, 0)
        setContent {
            LighterTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(apps.size) {i ->
                            val app = apps[i]
                            val icon = app.loadIcon(packageManager)

                            StandardCardLayout(
                                modifier = Modifier.size(150.dp, 120.dp),
                                imageCard = { interactionSource ->
                                    CardLayoutDefaults.ImageCard(
                                        onClick = {
                                            launchApp(app.activityInfo.packageName)
                                        },
                                        interactionSource = interactionSource
                                    ) {
                                        Image(
                                            painter = BitmapPainter(icon.toBitmap().asImageBitmap()),
                                            contentDescription = null,
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

    private fun launchApp(packageName: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            startActivity(intent)
        }
    }
}
