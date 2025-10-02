package com.example.ralfyfrias_ap2_p1.domain.usecase

import com.example.ralfyfrias_ap2_p1.domain.model.EntradaHuacales
import com.example.ralfyfrias_ap2_p1.domain.repository.EntradaHuacalesRepository

class GetEntradasUseCase(
    private val repository: EntradaHuacalesRepository
) {
    suspend operator fun invoke(): List<EntradaHuacales> {
        return repository.listar()
    }
}