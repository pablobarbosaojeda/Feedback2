package com.example.feedback2

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class NovelasWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Inicia una actualización de los datos del widget con WorkManager
        val workRequest = OneTimeWorkRequestBuilder<NovelasWidgetWorker>().build()
        WorkManager.getInstance(context.applicationContext).enqueue(workRequest)

        // Configura cada widget para responder a clics y actualizar la vista
        for (appWidgetId in appWidgetIds) {
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Configura la vista del widget y el clic en el contenedor
            val views = RemoteViews(context.packageName, R.layout.novelas_widget)
            views.setOnClickPendingIntent(R.id.widgetContainer, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
