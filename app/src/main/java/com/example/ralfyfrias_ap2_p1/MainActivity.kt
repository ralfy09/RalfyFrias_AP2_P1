package com.example.ralfyfrias_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.ralfyfrias_ap2_p1.data.local.database.AppDatabase
import com.example.ralfyfrias_ap2_p1.data.repository.EntradaHuacalesRepositoryImpl
import com.example.ralfyfrias_ap2_p1.domain.usecase.CountEntradasUseCase
import com.example.ralfyfrias_ap2_p1.domain.usecase.DeleteEntradaUseCase
import com.example.ralfyfrias_ap2_p1.domain.usecase.FilterEntradasUseCase
import com.example.ralfyfrias_ap2_p1.domain.usecase.GetEntradasUseCase
import com.example.ralfyfrias_ap2_p1.domain.usecase.InsertEntradaUseCase
import com.example.ralfyfrias_ap2_p1.domain.usecase.TotalEntradasUseCase
import com.example.ralfyfrias_ap2_p1.domain.usecase.UpdateEntradaUseCase
import com.example.ralfyfrias_ap2_p1.presentation.list.EntradaHuacalesViewModel
import com.example.ralfyfrias_ap2_p1.presentation.list.EntradaHuacalesViewModelFactory
import com.example.ralfyfrias_ap2_p1.ui.theme.RalfyFrias_AP2_P1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app_db"
            ).build()

            val repository = EntradaHuacalesRepositoryImpl(db.entradaHuacalesDao())

            val factory = EntradaHuacalesViewModelFactory(
                InsertEntradaUseCase(repository),
                UpdateEntradaUseCase(repository),
                DeleteEntradaUseCase(repository),
                GetEntradasUseCase(repository),
                FilterEntradasUseCase(repository),
                CountEntradasUseCase(repository),
                TotalEntradasUseCase(repository)
            )

            val navController = rememberNavController()
            val viewModel: EntradaHuacalesViewModel = viewModel(factory = factory)

            MyApp(navController, viewModel)
        }
    }
}