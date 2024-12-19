package com.andrea.compose_pizzeria.data.model

data class IngredienteDTO (
    val id: Int =0,
    val nombre: String = "",
    val alergenos: List<String> = emptyList()
)
