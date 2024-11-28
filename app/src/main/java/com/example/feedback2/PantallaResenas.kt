package com.example.feedback2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PantallaResenas(novelas: List<Novela>, onSeleccionarNovela: (Novela) -> Unit) {
    if (novelas.isEmpty()) {
        // Mostrar un mensaje cuando no hay novelas
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay reseñas disponibles",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    } else {
        // LazyColumn para mostrar la lista de novelas
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Uso de items para iterar sobre la lista de novelas
            items(novelas) { novela ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSeleccionarNovela(novela) } // Al hacer clic, se selecciona la novela
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Mostrar el título y el autor de cada novela
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = novela.titulo,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Autor: ${novela.autor}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaResenas() {
    // Ejemplo de novelas para la vista previa
    PantallaResenas(
        novelas = listOf(
            Novela("Novela 1", "Autor 1", 2022, "Sinopsis 1"),
            Novela("Novela 2", "Autor 2", 2021, "Sinopsis 2")
        ),
        onSeleccionarNovela = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaResenasVacia() {
    // Vista previa con lista vacía
    PantallaResenas(
        novelas = emptyList(),
        onSeleccionarNovela = {}
    )
}
