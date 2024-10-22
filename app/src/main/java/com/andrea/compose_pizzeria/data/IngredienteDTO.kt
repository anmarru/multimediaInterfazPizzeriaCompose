package com.andrea.compose_pizzeria.data

data class IngredienteDTO (
    val id: Int,
    val nombre: String,
    val alergenos: List<String>
)
