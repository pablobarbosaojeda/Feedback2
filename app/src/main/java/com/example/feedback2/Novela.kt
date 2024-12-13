package com.example.feedback2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Novela(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int,
    val sinopsis: String,
    val direccion: String, // Nuevo campo
    var esFavorita: Boolean = false,
    val resenas: MutableList<String> = mutableListOf()
) : Parcelable
