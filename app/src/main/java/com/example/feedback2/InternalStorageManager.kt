package com.example.feedback2

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class InternalStorageManager(private val context: Context) {

    fun saveData(fileName: String, data: String): Boolean {
        return try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                output.write(data.toByteArray())
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun readData(fileName: String): String? {
        return try {
            context.openFileInput(fileName).use { input ->
                input.readBytes().toString(Charsets.UTF_8)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
