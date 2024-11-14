package com.example.feedback2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FragmentListaNovelas(
    novelas: List<Novela>,
    onVerDetallesClick: (Novela) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(novelas) { novela ->
            NovelaItem(
                novela = novela,
                onVerDetallesClick = { onVerDetallesClick(novela) },
                onEliminarClick = { /* Implementa la acción de eliminar si es necesario */ }
            )
        }
    }
}
