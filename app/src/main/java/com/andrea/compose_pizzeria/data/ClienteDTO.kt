package com.andrea.compose_pizzeria.data

data class ClienteDTO (
    val id: Int,
    val dni: String,
    val direccion: String,
    val telefono: String,
    val email: String,
    val nombre: String,
    val password: String,
    val listaPedidos: List<PedidoDTO>
)