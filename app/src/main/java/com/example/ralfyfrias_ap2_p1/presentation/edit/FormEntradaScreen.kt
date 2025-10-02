package com.example.ralfyfrias_ap2_p1.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ralfyfrias_ap2_p1.domain.model.EntradaHuacales
import com.example.ralfyfrias_ap2_p1.presentation.list.EntradaHuacalesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEntradaScreen(
    viewModel: EntradaHuacalesViewModel,
    navController: NavHostController,
    editar: EntradaHuacales? = null
) {
    var fecha by remember { mutableStateOf(editar?.fecha ?: "") }
    var cliente by remember { mutableStateOf(editar?.nombreCliente ?: "") }
    var cantidad by remember { mutableStateOf(editar?.cantidad?.toString() ?: "") }
    var precio by remember { mutableStateOf(editar?.precio?.toString() ?: "") }

    val cantidadNum = cantidad.toDoubleOrNull() ?: 0.0
    val precioNum = precio.toDoubleOrNull() ?: 0.0
    val importe = cantidadNum * precioNum

    val isFormValid = fecha.isNotBlank() && cliente.isNotBlank() && cantidad.isNotBlank() && precio.isNotBlank()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (editar == null) "Nueva Entrada" else "Editar Entrada") }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = fecha,
                        onValueChange = { fecha = it },
                        label = { Text("Fecha (YYYY-MM-DD)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = cliente,
                        onValueChange = { cliente = it },
                        label = { Text("Nombre del Cliente") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = cantidad,
                        onValueChange = { cantidad = it },
                        label = { Text("Cantidad") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    OutlinedTextField(
                        value = precio,
                        onValueChange = { precio = it },
                        label = { Text("Precio") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    OutlinedTextField(
                        value = "%.2f".format(importe),
                        onValueChange = {},
                        label = { Text("Importe") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        val entrada = EntradaHuacales(
                            idEntrada = editar?.idEntrada ?: 0,                            fecha = fecha,
                            nombreCliente = cliente,
                            cantidad = cantidadNum.toInt(),
                            precio = precioNum
                        )
                        if (editar == null) viewModel.insertar(entrada)
                        else viewModel.actualizar(entrada)
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f),
                    enabled = isFormValid
                ) {
                    Text("Guardar")
                }

                if (editar != null) {
                    OutlinedButton(
                        onClick = {
                            viewModel.eliminar(editar)
                            navController.popBackStack()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Eliminar")
                    }
                }
            }

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}
