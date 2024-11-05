package com.andrea.compose_pizzeria.data

data class ProductoDTO(
    val id: Int,
    val nombre: String,
    val precio: Float,
    val size: SIZE,
    val ingredientes: List<IngredienteDTO>,
    val tipo: TIPO
)
