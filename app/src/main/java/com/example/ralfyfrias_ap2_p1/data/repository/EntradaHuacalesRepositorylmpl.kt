package com.example.ralfyfrias_ap2_p1.data.repository

import com.example.ralfyfrias_ap2_p1.data.local.dao.EntradaHuacalesDao
import com.example.ralfyfrias_ap2_p1.data.local.mapper.toDomain
import com.example.ralfyfrias_ap2_p1.data.local.mapper.toEntity
import com.example.ralfyfrias_ap2_p1.domain.model.EntradaHuacales
import com.example.ralfyfrias_ap2_p1.domain.repository.EntradaHuacalesRepository

class EntradaHuacalesRepositoryImpl(private val dao: EntradaHuacalesDao) :
    EntradaHuacalesRepository {
    override suspend fun insertar(e: EntradaHuacales) = dao.insertar(e.toEntity())
    override suspend fun actualizar(e: EntradaHuacales) = dao.actualizar(e.toEntity())
    override suspend fun eliminar(e: EntradaHuacales) = dao.eliminar(e.toEntity())
    override suspend fun listar() = dao.listar().map { it.toDomain() }
    override suspend fun filtrar(cliente: String?, fecha: String?) = dao.filtrar(cliente, fecha).map { it.toDomain() }
    override suspend fun contar() = dao.contar()
    override suspend fun total() = dao.total() ?: 0.0
}