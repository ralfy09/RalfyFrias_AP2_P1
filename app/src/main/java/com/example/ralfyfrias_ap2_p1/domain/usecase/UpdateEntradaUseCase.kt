package com.example.ralfyfrias_ap2_p1.domain.usecase

import com.example.ralfyfrias_ap2_p1.domain.model.EntradaHuacales
import com.example.ralfyfrias_ap2_p1.domain.repository.EntradaHuacalesRepository

class UpdateEntradaUseCase(
    private val repository: EntradaHuacalesRepository
) {
    suspend operator fun invoke(entrada: EntradaHuacales) {
        repository.actualizar(entrada)
    }
}