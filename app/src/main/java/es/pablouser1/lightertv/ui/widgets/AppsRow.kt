package es.pablouser1.lightertv.ui.widgets

import android.content.Context
import android.content.pm.ResolveInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.material3.CardLayoutDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.StandardCardLayout
import androidx.tv.material3.Text
import es.pablouser1.lightertv.helpers.Apps

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun AppsRow(apps: List<ResolveInfo>, context: Context) {
    LazyRow(
        modifier = Modifier.padding(30.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(apps.size) {i ->
            val app = apps[i]
            val icon = app.loadIcon(context.packageManager)
            val name = app.activityInfo.packageName

            StandardCardLayout(
                modifier = Modifier.size(180.dp, 160.dp),
                imageCard = { interactionSource ->
                    CardLayoutDefaults.ImageCard(
                        onClick = {
                            Apps.launch(name, context)
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
                    Text(app.loadLabel(context.packageManager).toString())
                }
            )
        }
    }
}