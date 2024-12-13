package com.example.feedback2

import android.Manifest
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel
import java.nio.file.StandardOpenOption

class MainActivity : ComponentActivity() {
    private lateinit var preferencesManager: PreferencesManager
    private lateinit var internalStorageManager: InternalStorageManager
    private lateinit var externalStorageManager: ExternalStorageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferencesManager(this)
        internalStorageManager = InternalStorageManager(this)
        externalStorageManager = ExternalStorageManager(this)

        // Aplicar tema basado en la preferencia
        val isDarkMode = preferencesManager.isDarkMode()
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        // Solicitar permisos
        requestStoragePermissions()

        setContent {
            NovelaApp()
        }

        scheduleJob()
    }

    private fun requestStoragePermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Programar JobScheduler para sincronización en segundo plano
    private fun scheduleJob() {
        val componentName = ComponentName(this, DataSyncJobService::class.java)
        val jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) // Solo Wi-Fi
            .setPeriodic(15 * 60 * 1000) // Cada 15 minutos
            .build()

        val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }

    companion object {
        const val REQUEST_CODE_STORAGE = 1
        private const val JOB_ID = 1
    }
}

fun backupDatabase(context: Context) {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as MainActivity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            MainActivity.REQUEST_CODE_STORAGE
        )
        return
    }

    val dbFile = context.getDatabasePath("novelas.db")
    val backupFile = File(
        context.getExternalFilesDir(null),
        "novelas_backup_${System.currentTimeMillis()}.db"
    )

    try {
        FileInputStream(dbFile).use { source ->
            FileOutputStream(backupFile).use { destination ->
                destination.channel.transferFrom(source.channel, 0, source.channel.size())
            }
        }
        Toast.makeText(context, "Respaldo exitoso en: ${backupFile.path}", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        Toast.makeText(context, "Respaldo fallido: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

fun restoreDatabase(context: Context) {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as MainActivity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            MainActivity.REQUEST_CODE_STORAGE
        )
        return
    }

    val dbFile = context.getDatabasePath("novelas.db")
    val backupFile = File(
        context.getExternalFilesDir(null),
        "novelas_backup.db"
    )

    try {
        FileInputStream(backupFile).use { source ->
            FileOutputStream(dbFile).use { destination ->
                destination.channel.transferFrom(source.channel, 0, source.channel.size())
            }
        }
        Toast.makeText(context, "Restauración exitosa", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        Toast.makeText(context, "Restauración fallida: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovelaApp() {
    val navController = rememberNavController()
    val viewModel: NovelaViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gestión de Novelas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationHost(navController = navController, viewModel = viewModel)
            }
        }
    )
}

@Composable
fun NavigationHost(navController: NavHostController, viewModel: NovelaViewModel) {
    val novelas by viewModel.novelas.observeAsState(emptyList())

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            PantallaPrincipal(
                novelas = novelas,
                onAgregarClick = { navController.navigate("agregar") },
                onEliminarClick = { novela -> viewModel.eliminarNovela(novela) },
                onVerDetallesClick = { novela ->
                    navController.navigate("detalles/${novela.titulo}")
                },
                onSettingsClick = { navController.navigate("settings") }
            )
        }
    }
}
