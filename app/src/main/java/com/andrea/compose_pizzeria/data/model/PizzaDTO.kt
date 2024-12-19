package com.andrea.compose_pizzeria.data.model

data class PizzaDTO (
    val id: Int,
    val nombre: String,
    val precio: Float,
    val size: SIZE,
    val ingredientes: List<IngredienteDTO>
)