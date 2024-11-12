package es.pablouser1.lightertv.helpers

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo

class Apps {
    companion object {
        fun all(context: Context): List<ResolveInfo> {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            return context.packageManager.queryIntentActivities(intent, 0)
        }

        fun launch(packageName: String, context: Context) {
            val intent = context.packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                context.startActivity(intent)
            }
        }
    }
}