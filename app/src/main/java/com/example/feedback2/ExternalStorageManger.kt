package com.example.feedback2

import android.content.Context
import android.os.Environment
import java.io.*

class ExternalStorageManager(private val context: Context) {

    fun saveData(fileName: String, data: String): Boolean {
        return try {
            if (isExternalStorageWritable()) {
                val file = File(context.getExternalFilesDir(null), fileName)
                FileOutputStream(file).use { output ->
                    output.write(data.toByteArray())
                }
                true
            } else {
                false
            }
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun readData(fileName: String): String? {
        return try {
            if (isExternalStorageReadable()) {
                val file = File(context.getExternalFilesDir(null), fileName)
                FileInputStream(file).use { input ->
                    input.readBytes().toString(Charsets.UTF_8)
                }
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isExternalStorageReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED || state == Environment.MEDIA_MOUNTED_READ_ONLY
    }
}
