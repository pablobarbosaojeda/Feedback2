// File: app/src/main/java/com/example/feedback2/NovelaLoader.kt
package com.example.feedback2

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class NovelaLoader(context: Context) : AsyncTaskLoader<List<Novela>>(context) {
    override fun loadInBackground(): List<Novela>? {
        // Load data from the database or remote server
        // ...
        return listOf()
    }

    override fun onStartLoading() {
        forceLoad()
    }
}