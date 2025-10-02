package com.example.ralfyfrias_ap2_p1.data.local.mapper

import com.example.ralfyfrias_ap2_p1.data.local.entities.EntradaHuacalesEntity
import com.example.ralfyfrias_ap2_p1.domain.model.EntradaHuacales

// De Entity → Model (para UI/Domain)
fun EntradaHuacalesEntity.toDomain(): EntradaHuacales =
    EntradaHuacales(
        idEntrada = idEntrada,
        fecha = fecha,
        nombreCliente = nombreCliente,
        cantidad = cantidad,
        precio = precio
    )

// De Model → Entity (para DB/DAO)
fun EntradaHuacales.toEntity(): EntradaHuacalesEntity =
    EntradaHuacalesEntity(
        idEntrada = idEntrada,
        fecha = fecha,
        nombreCliente = nombreCliente,
        cantidad = cantidad,
        precio = precio
    )