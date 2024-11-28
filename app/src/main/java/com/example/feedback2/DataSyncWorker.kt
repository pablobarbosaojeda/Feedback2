package com.example.feedback2

import android.content.Context
import android.os.BatteryManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class DataSyncWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        try {
            // Verificar el estado de la batería antes de iniciar la sincronización
            if (isBatteryLow()) {
                Log.d(TAG, "Sincronización cancelada: batería baja")
                return Result.failure() // Finalizar con falla sin reintento
            }

            Log.d(TAG, "Iniciando sincronización de datos...")
            // Lógica de sincronización de datos con el servidor
            // Aquí puedes implementar tu lógica para interactuar con la red o base de datos remota
            // Ejemplo: llamada a una API
            syncData()

            Log.d(TAG, "Sincronización completada exitosamente")
            return Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error durante la sincronización", e)
            return Result.retry() // Reintentar en caso de error
        }
    }

    private fun isBatteryLow(): Boolean {
        val batteryManager = applicationContext.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        return batteryLevel in 0..15 // Considera batería baja si está por debajo del 15%
    }

    private suspend fun syncData() {
        // Implementa la lógica de sincronización aquí
        // Ejemplo: llamada a una API para obtener o enviar datos
        Log.d(TAG, "Sincronizando datos con el servidor...")
        // Simulación de trabajo intensivo
        kotlinx.coroutines.delay(2000) // Simula una operación que tarda 2 segundos
    }

    companion object {
        private const val TAG = "DataSyncWorker"
    }
}
