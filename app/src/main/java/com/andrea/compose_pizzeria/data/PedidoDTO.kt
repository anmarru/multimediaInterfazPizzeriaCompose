package com.andrea.compose_pizzeria.data

import java.util.Date

data class PedidoDTO (
    val id: Int,
    val fecha: Date,
    val estadoPedido: ESTADO_PEDIDO,
    val precioTotal: Float,
    val lineaPedidos: List<LineaPedidoDTO>

)