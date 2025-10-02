package com.example.ralfyfrias_ap2_p1.domain.usecase

import com.example.ralfyfrias_ap2_p1.domain.repository.EntradaHuacalesRepository

class CountEntradasUseCase(
    private val repository: EntradaHuacalesRepository
) {
    suspend operator fun invoke(): Int {
        return repository.contar()
    }
}