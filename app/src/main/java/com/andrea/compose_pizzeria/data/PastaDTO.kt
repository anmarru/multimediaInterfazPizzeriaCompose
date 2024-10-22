package com.andrea.compose_pizzeria.data

data class PastaDTO (
    val id: Int,
    val nombre: String,
    val precio: Float,
    val ingredientes: List<IngredienteDTO>

)