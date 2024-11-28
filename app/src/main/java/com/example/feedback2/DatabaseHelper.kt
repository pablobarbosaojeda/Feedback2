package com.example.feedback2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.ref.WeakReference

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "novelas.db"
        const val DATABASE_VERSION = 2

        const val TABLE_NOVELAS = "novelas"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_FAVORITE = "esFavorita"

        // Singleton para optimizar el acceso a la base de datos
        @Volatile
        private var instance: WeakReference<DatabaseHelper>? = null

        fun getInstance(context: Context): DatabaseHelper {
            return instance?.get() ?: synchronized(this) {
                val newInstance = DatabaseHelper(context.applicationContext)
                instance = WeakReference(newInstance)
                newInstance
            }
        }
    }

    private val CREATE_TABLE_NOVELAS = """
        CREATE TABLE $TABLE_NOVELAS (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_TITLE TEXT NOT NULL,
            $COLUMN_AUTHOR TEXT,
            $COLUMN_DESCRIPTION TEXT,
            $COLUMN_FAVORITE INTEGER DEFAULT 0
        )
    """

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_NOVELAS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE $TABLE_NOVELAS ADD COLUMN $COLUMN_FAVORITE INTEGER DEFAULT 0")
        }
    }
}
