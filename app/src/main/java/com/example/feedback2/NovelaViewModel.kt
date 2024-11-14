package com.example.feedback2
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feedback2.Novela
import com.example.feedback2.NovelaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovelaViewModel(application: Application) : AndroidViewModel(application) {
    private val novelaDao = NovelaDao(application)

    private val _novelas = MutableLiveData<List<Novela>>()
    val novelas: LiveData<List<Novela>> get() = _novelas

    init {
        loadNovelas()
    }

    private fun loadNovelas() {
        viewModelScope.launch(Dispatchers.IO) {
            val novelasFromDb = novelaDao.getAllNovelas()
            _novelas.postValue(novelasFromDb)
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
            novelaDao.updateNovela(novelaActualizada) // Asegúrate de que `updateNovela` esté implementado en `NovelaDao`
            loadNovelas() // Recarga la lista de novelas para reflejar los cambios
        }
    }

    fun agregarResena(novela: Novela, resena: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Lógica para agregar la reseña en la base de datos si es necesario
            loadNovelas() // Recarga la lista para reflejar el cambio en la interfaz
        }
    }
}
