package com.example.feedback2

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.BatteryManager
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DataSyncJobService : JobService() {

    private var job: Job? = null

    override fun onStartJob(params: JobParameters?): Boolean {
        job = CoroutineScope(Dispatchers.IO).launch {
            performDataSync(params)
        }
        return true // Indica que el trabajo continúa en segundo plano
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        job?.cancel() // Cancelar el trabajo en caso de interrupción
        return true // Reintentar el trabajo si fue interrumpido
    }

    private suspend fun performDataSync(params: JobParameters?) {
        try {
            // Verificar el estado de la batería antes de iniciar la sincronización
            if (isBatteryLow()) {
                Log.d(TAG, "Sincronización cancelada: batería baja")
                jobFinished(params, false) // Finalizar sin reintento
                return
            }

            Log.d(TAG, "Sincronizando datos con el servidor...")
            // Lógica de sincronización de datos
            // Aquí puedes implementar tu lógica para interactuar con la red o base de datos remota
            // ...
            Log.d(TAG, "Sincronización completada exitosamente")
            jobFinished(params, false) // Finalizar sin reintento
        } catch (e: Exception) {
            Log.e(TAG, "Error durante la sincronización", e)
            jobFinished(params, true) // Reintentar en caso de error
        }
    }

    private fun isBatteryLow(): Boolean {
        val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
        val batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        return batteryLevel in 0..15 // Considera batería baja si está por debajo del 15%
    }

    companion object {
        private const val TAG = "DataSyncJobService"
    }
}
