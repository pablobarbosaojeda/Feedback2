package com.example.feedback2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPrincipal(
    novelas: List<Novela>,
    onAgregarClick: () -> Unit,
    onEliminarClick: (Novela) -> Unit,
    onVerDetallesClick: (Novela) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Novela")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(novelas) { novela ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onVerDetallesClick(novela) }
                            .padding(8.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = novela.titulo, style = MaterialTheme.typography.titleLarge)
                            Text(text = "Autor: ${novela.autor}")
                        }
                        if (novela.esFavorita) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "Favorita",
                                tint = Color.Yellow
                            )
                        }
                        IconButton(onClick = { onEliminarClick(novela) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar Novela")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaPrincipal() {
    PantallaPrincipal(
        novelas = listOf(
            Novela("Novela 1", "Autor 1", 2022, "Sinopsis 1"),
            Novela("Novela 2", "Autor 2", 2021, "Sinopsis 2", esFavorita = true)
        ),
        onAgregarClick = {},
        onEliminarClick = {},
        onVerDetallesClick = {}
    )
}
