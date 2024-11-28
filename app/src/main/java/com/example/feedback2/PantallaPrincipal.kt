package com.example.feedback2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaPrincipal(
    novelas: List<Novela> = listOf(
        Novela("Novela de Ejemplo", "Autor Ejemplo", 2022, "Sinopsis"),
    ),
    onAgregarClick: () -> Unit,
    onEliminarClick: (Novela) -> Unit,
    onVerDetallesClick: (Novela) -> Unit,
    onSettingsClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Novela")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (novelas.isEmpty()) {
                // Mostrar un mensaje si la lista está vacía
                Text(
                    text = "No hay novelas disponibles",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Gestión de Novelas",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Button(
                        onClick = onSettingsClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text("Configuración")
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(novelas) { novela ->
                            NovelaItem(
                                novela = novela,
                                onVerDetallesClick = { onVerDetallesClick(novela) },
                                onEliminarClick = { onEliminarClick(novela) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NovelaItem(
    novela: Novela,
    onVerDetallesClick: () -> Unit,
    onEliminarClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onVerDetallesClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = novela.titulo, style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Autor: ${novela.autor}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (novela.esFavorita) {
            Icon(
                Icons.Default.Star,
                contentDescription = "Marcar como favorita",
                tint = Color.Yellow
            )
        }
        IconButton(onClick = onEliminarClick) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Eliminar novela"
            )
        }
    }
}
