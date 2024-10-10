// File: app/src/main/java/com/example/feedback2/DataSyncJobService.kt
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.feedback2.R

private fun showNotification(context: Context, message: String) {
    val channelId = "data_sync_channel"
    val channelName = "Data Sync"
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    val notification = NotificationCompat.Builder(context, channelId)
        .setContentTitle("Data Sync")
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_sync)
        .build()

    notificationManager.notify(1, notification)
}