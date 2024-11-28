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
        Log.d(TAG, "doWork: Iniciando actualización del widget")
        try {
            val context = applicationContext
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(
                ComponentName(context, NovelasWidgetProvider::class.java)
            )

            if (appWidgetIds.isEmpty()) {
                Log.d(TAG, "No hay widgets activos para actualizar")
                return@withContext Result.success()
            }

            val favoritosText = obtenerTextoFavoritos(context)
            if (favoritosText == null) {
                Log.e(TAG, "Error al obtener los favoritos")
                return@withContext Result.failure()
            }

            actualizarWidgets(appWidgetManager, appWidgetIds, favoritosText, context)

            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error al actualizar los widgets", e)
            Result.failure()
        }
    }

    private suspend fun obtenerTextoFavoritos(context: Context): String? = withContext(Dispatchers.IO) {
        try {
            val novelaDao = NovelaDao(context) // Asume que tienes implementado un DAO para la base de datos
            val favoritos = novelaDao.getFavoriteNovelas()

            return@withContext if (favoritos.isNotEmpty()) {
                favoritos.joinToString(separator = "\n• ") { it.titulo }.prependIndent("• ")
            } else {
                "No hay novelas favoritas"
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener los favoritos de la base de datos", e)
            null
        }
    }

    private fun actualizarWidgets(
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        favoritosText: String,
        context: Context
    ) {
        appWidgetIds.forEach { appWidgetId ->
            val views = RemoteViews(context.packageName, R.layout.novelas_widget)
            views.setTextViewText(R.id.favoritesList, favoritosText)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
        Log.d(TAG, "Widgets actualizados exitosamente")
    }

    companion object {
        private const val TAG = "NovelasWidgetWorker"
    }
}
