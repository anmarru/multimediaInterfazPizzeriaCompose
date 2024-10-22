package com.andrea.compose_pizzeria.data

data class LineaPedidoDTO (
    val id: Int,
    val cantidad: Int,
    val pizza: PizzaDTO?,
    val pasta: PastaDTO?,
    val bebida: BebidaDTO?
)