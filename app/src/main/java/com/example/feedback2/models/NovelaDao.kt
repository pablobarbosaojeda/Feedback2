package com.example.feedback2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class NovelaDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun addNovela(novela: Novela): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, novela.titulo)
            put(DatabaseHelper.COLUMN_AUTHOR, novela.autor)
            put(DatabaseHelper.COLUMN_DESCRIPTION, novela.sinopsis)
            put(DatabaseHelper.COLUMN_FAVORITE, if (novela.esFavorita) 1 else 0)
        }
        return db.insert(DatabaseHelper.TABLE_NOVELAS, null, values)
    }

    fun getAllNovelas(): List<Novela> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor =
            db.query(DatabaseHelper.TABLE_NOVELAS, null, null, null, null, null, null)
        val novelas = mutableListOf<Novela>()
        with(cursor) {
            while (moveToNext()) {
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_AUTHOR))
                val description =
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION))
                val esFavorita = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_FAVORITE)) == 1
                novelas.add(Novela(title, author, 0, description, esFavorita))
            }
        }
        cursor.close()
        return novelas
    }

    fun updateNovela(novela: Novela): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, novela.titulo)
            put(DatabaseHelper.COLUMN_AUTHOR, novela.autor)
            put(DatabaseHelper.COLUMN_DESCRIPTION, novela.sinopsis)
            put(DatabaseHelper.COLUMN_FAVORITE, if (novela.esFavorita) 1 else 0)
        }
        return db.update(
            DatabaseHelper.TABLE_NOVELAS,
            values,
            "${DatabaseHelper.COLUMN_TITLE} = ?",
            arrayOf(novela.titulo)
        )
    }

    fun deleteNovela(titulo: String): Int {
        val db = dbHelper.writableDatabase
        return db.delete(
            DatabaseHelper.TABLE_NOVELAS,
            "${DatabaseHelper.COLUMN_TITLE} = ?",
            arrayOf(titulo)
        )
    }


    fun getFavoriteNovelas(): List<Novela> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NOVELAS,
            null,
            "${DatabaseHelper.COLUMN_FAVORITE} = ?",
            arrayOf("1"),  // Solo selecciona las novelas marcadas como favoritas (1)
            null,
            null,
            null
        )

        val novelas = mutableListOf<Novela>()
        with(cursor) {
            while (moveToNext()) {
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_AUTHOR))
                val description =
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION))
                val esFavorita = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_FAVORITE)) == 1
                novelas.add(Novela(title, author, 0, description, esFavorita))
            }
        }
        cursor.close()
        return novelas
    }
}

