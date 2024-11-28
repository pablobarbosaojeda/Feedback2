package com.example.feedback2

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.logging.Level
import java.util.logging.Logger

class NovelasWidgetProvider : AppWidgetProvider() {

    private val logger = Logger.getLogger(NovelasWidgetProvider::class.java.name)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        try {
            // Inicia una actualización de los datos del widget con WorkManager
            val workRequest = OneTimeWorkRequestBuilder<NovelasWidgetWorker>().build()
            WorkManager.getInstance(context.applicationContext).enqueue(workRequest)

            // Configura cada widget para responder a clics y actualizar la vista
            for (appWidgetId in appWidgetIds) {
                updateWidget(context, appWidgetManager, appWidgetId)
            }
        } catch (e: Exception) {
            logger.log(Level.SEVERE, "Error al actualizar los widgets", e)
        }
    }

    private fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Configura la vista del widget y el clic en el contenedor
        val views = RemoteViews(context.packageName, R.layout.novelas_widget)
        views.setOnClickPendingIntent(R.id.widgetContainer, pendingIntent)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // Maneja la eliminación de widgets si es necesario
        logger.log(Level.INFO, "Widgets eliminados: ${appWidgetIds.joinToString()}")
        super.onDeleted(context, appWidgetIds)
    }

    override fun onEnabled(context: Context) {
        // Configuración inicial si es necesario
        logger.log(Level.INFO, "Widget habilitado")
        super.onEnabled(context)
    }

    override fun onDisabled(context: Context) {
        // Limpieza al deshabilitar el último widget
        logger.log(Level.INFO, "Widget deshabilitado")
        super.onDisabled(context)
    }
}
