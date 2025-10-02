package com.example.ralfyfrias_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ralfyfrias_ap2_p1.domain.model.EntradaHuacales

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEntradasScreen(
    viewModel: EntradaHuacalesViewModel,
    navController: NavHostController
) {
    val entradas by viewModel.entradas.collectAsState()
    val conteo by viewModel.conteo.collectAsState()
    val total by viewModel.total.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Entradas de Huacales") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("form") }) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total registros: $conteo")
                    Text("Total: $${"%,.2f".format(total)}")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(entradas) { entrada ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("form/${entrada.idEntrada}")
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = entrada.nombreCliente,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${entrada.cantidad} x $${entrada.precio}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = entrada.fecha,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "= $${"%,.2f".format(entrada.cantidad * entrada.precio)}",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
