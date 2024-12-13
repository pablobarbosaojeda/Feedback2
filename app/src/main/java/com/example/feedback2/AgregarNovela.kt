@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feedback2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedback2.ui.theme.*

@Composable
fun AgregarNovela(onAgregarNovela: (Novela) -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") } // Nuevo campo

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(BackgroundColor)
            .fillMaxSize()
    ) {
        Text(
            text = "Agregar Novela",
            style = MaterialTheme.typography.headlineSmall,
            color = PrimaryColor,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SurfaceColor,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = PrimaryLightColor
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = autor,
            onValueChange = { autor = it },
            label = { Text("Autor") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SurfaceColor,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = PrimaryLightColor
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = anio,
            onValueChange = { anio = it },
            label = { Text("Año de Publicación") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SurfaceColor,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = PrimaryLightColor
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = sinopsis,
            onValueChange = { sinopsis = it },
            label = { Text("Sinopsis") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SurfaceColor,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = PrimaryLightColor
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Nuevo campo para Dirección
        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SurfaceColor,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = PrimaryLightColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (titulo.isNotEmpty() && autor.isNotEmpty() && anio.isNotEmpty() && sinopsis.isNotEmpty() && direccion.isNotEmpty()) {
                    onAgregarNovela(Novela(titulo, autor, anio.toInt(), sinopsis, direccion = direccion))
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
        ) {
            Text("Agregar Novela", color = Color.White)
        }

        // Imagen del mapa agregada en el espacio en blanco
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.mapa), // Asegúrate de usar tu recurso real
            contentDescription = "Mapa",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}
