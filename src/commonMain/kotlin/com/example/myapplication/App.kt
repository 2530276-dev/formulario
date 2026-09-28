package com.example.myapplication


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        // Estados para los campos del formulario (Aplicación 5)
        var nombre by remember { mutableStateOf("") }
        var matricula by remember { mutableStateOf("") }
        var asignatura by remember { mutableStateOf("") }
        var hora by remember { mutableStateOf("") }
        var fecha by remember { mutableStateOf("") }

        var mostrarResultado by remember { mutableStateOf(false) }
        var mensajeError by remember { mutableStateOf("") }
        var jsonResultado by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Componente Text básico (Aplicación 1 y 2)
            Text(
                text = "Práctica de programación (Formulario)",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = matricula,
                onValueChange = { matricula = it },
                label = { Text("Matrícula") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = asignatura,
                onValueChange = { asignatura = it },
                label = { Text("Asignatura") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = hora,
                onValueChange = { hora = it },
                label = { Text("Hora que se imparte") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha de entrega") },
                modifier = Modifier.fillMaxWidth()
            )

            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = {
                    if (nombre.isBlank() || matricula.isBlank() || asignatura.isBlank() || hora.isBlank() || fecha.isBlank()) {
                        mensajeError = "Por favor, completa todos los campos"
                        mostrarResultado = false
                    } else {
                        mensajeError = ""
                        // Estructura de datos simulada en formato JSON como lo solicitó el profesor
                        jsonResultado = """
                            {
                              "nombre": "$nombre",
                              "matricula": "$matricula",
                              "asignatura": "$asignatura",
                              "hora": "$hora",
                              "fecha": "$fecha"
                            }
                        """.trimIndent()
                        mostrarResultado = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }

            // Componente Card visual (Aplicación 4) ubicado abajo del botón
            AnimatedVisibility(visible = mostrarResultado) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Datos Registrados (Formato JSON):",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = jsonResultado,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}


