package com.example.feedback2

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NovelasWidgetWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d("NovelasWidgetWorker", "doWork: Actualizando el widget")
        try {
            val context = applicationContext
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(
                ComponentName(context, NovelasWidgetProvider::class.java)
            )

            val novelaDao = NovelaDao(context)  // Asume que tienes implementado un DAO para tu base de datos
            val favoritos = novelaDao.getFavoriteNovelas()

            val favoritosText = if (favoritos.isNotEmpty()) {
                favoritos.joinToString(separator = "\n• ") { it.titulo }.prependIndent("• ")
            } else {
                "No hay novelas favoritas"
            }

            appWidgetIds.forEach { appWidgetId ->
                val views = RemoteViews(context.packageName, R.layout.novelas_widget)
                views.setTextViewText(R.id.favoritesList, favoritosText)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }

            Result.success()
        } catch (e: Exception) {
            Log.e("NovelasWidgetWorker", "Error actualizando el widget", e)
            Result.failure()
        }
    }
}
