package com.example.feedback2

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NovelaViewModel : ViewModel() {

    //Lista de novelas en memoria
    private val _novelas = mutableStateListOf<Novela>()
    val novelas: List<Novela> get() = _novelas

    fun agregarNovela(novela: Novela) {
        _novelas.add(novela)
    }

    fun eliminarNovela(novela: Novela) {
        _novelas.remove(novela)
    }

    fun marcarFavorita(novela: Novela) {
        val index = _novelas.indexOf(novela)
        if (index >= 0) {
            _novelas[index] = _novelas[index].copy(esFavorita = !novela.esFavorita)
        }
    }

    fun agregarResena(novela: Novela, resena: String) {
        novela.resenas.add(resena)
    }
}