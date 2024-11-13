package com.example.feedback2

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovelaViewModel(application: Application) : AndroidViewModel(application) {
    private val novelaDao = NovelaDao(application)
    private val _novelas = mutableStateListOf<Novela>()
    val novelas: List<Novela> get() = _novelas

    init {
        loadNovelas()
    }

    private fun loadNovelas() {
        viewModelScope.launch(Dispatchers.IO) {
            val novelasFromDb = novelaDao.getAllNovelas()
            _novelas.clear()
            _novelas.addAll(novelasFromDb)
        }
    }

    fun agregarNovela(novela: Novela) {
        viewModelScope.launch(Dispatchers.IO) {
            novelaDao.addNovela(novela)
            loadNovelas()
        }
    }

    fun eliminarNovela(novela: Novela) {
        viewModelScope.launch(Dispatchers.IO) {
            novelaDao.deleteNovela(novela.titulo)
            loadNovelas()
        }
    }

    fun marcarFavorita(novela: Novela) {
        viewModelScope.launch(Dispatchers.IO) {
            val novelaActualizada = novela.copy(esFavorita = !novela.esFavorita)
            novelaDao.updateNovela(novelaActualizada) // Asegura que updateNovela esté implementado en NovelaDao
            loadNovelas() // Recarga la lista de novelas para reflejar los cambios
        }
    }



    fun agregarResena(novela: Novela, resena: String) {
        val index = _novelas.indexOf(novela)
        if (index >= 0) {
            _novelas[index].resenas.add(resena)
        }
    }
}