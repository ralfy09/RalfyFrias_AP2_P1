package com.example.ralfyfrias_ap2_p1.domain.usecase

import com.example.ralfyfrias_ap2_p1.domain.model.EntradaHuacales
import com.example.ralfyfrias_ap2_p1.domain.repository.EntradaHuacalesRepository

class FilterEntradasUseCase(
    private val repository: EntradaHuacalesRepository
) {
    suspend operator fun invoke(cliente: String?, fecha: String?): List<EntradaHuacales> {
        return repository.filtrar(cliente, fecha)
    }
}