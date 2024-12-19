package com.andrea.compose_pizzeria.data.model

data class PastaDTO (
    val id: Int,
    val nombre: String,
    val precio: Float,
    val ingredientes: List<IngredienteDTO>

)