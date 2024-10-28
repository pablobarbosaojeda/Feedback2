package com.example.feedback2

import android.content.Context
import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel

@Composable
fun SettingsScreen(
    context: Context,
    onBackup: () -> Unit,
    onRestore: () -> Unit,
    onBack: () -> Unit
) {
    val preferencesManager = PreferencesManager(context)
    val isDarkMode = preferencesManager.isDarkMode()

    Column {
        Button(onClick = onBackup) {
            Text("Backup Data")
        }
        Button(onClick = onRestore) {
            Text("Restore Data")
        }
        Button(onClick = {
            preferencesManager.toggleTheme()
            (context as MainActivity).recreate() // Recreate activity to apply theme change
        }) {
            Text(if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode")
        }
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}