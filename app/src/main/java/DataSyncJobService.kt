import android.app.job.JobParameters
import android.app.job.JobService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.feedback2.R
import kotlinx.coroutines.*

class DataSyncJobService : JobService() {

    // Esta función es invocada cuando el JobScheduler inicia el trabajo
    override fun onStartJob(params: JobParameters?): Boolean {
        // Usar coroutines en lugar de AsyncTask para realizar la tarea en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            performDataSync()
            jobFinished(params, false)
        }
        return true // Indica que la tarea sigue ejecutándose en segundo plano
    }

    // Esta función se ejecuta si el trabajo es cancelado
    override fun onStopJob(params: JobParameters?): Boolean {
        return true // Indica si el trabajo debe volver a programarse
    }

    // Función que realiza la sincronización de datos
    private suspend fun performDataSync() {
        // Aquí iría la lógica para sincronizar los datos con el servidor remoto
        delay(3000) // Simulación de tarea de sincronización

        // Mostrar notificación al finalizar la sincronización
        withContext(Dispatchers.Main) {
            showNotification("Data synchronization completed")
        }
    }

    // Función que muestra una notificación cuando la sincronización termina
    private fun showNotification(message: String) {
        val channelId = "data_sync_channel"
        val channelName = "Data Sync"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Data Sync")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_sync)
            .build()

        notificationManager.notify(1, notification)
    }
}
