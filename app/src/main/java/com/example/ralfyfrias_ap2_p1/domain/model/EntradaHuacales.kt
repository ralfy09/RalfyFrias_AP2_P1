package com.example.ralfyfrias_ap2_p1.domain.model

data class EntradaHuacales(
    val idEntrada: Int = 0,
    val fecha: String,
    val nombreCliente: String,
    val cantidad: Int,
    val precio: Double
)