    package com.andrea.compose_pizzeria.data.model

    data class ProductoDTO(
        val id: Int=0,
        val nombre: String,
        val precio: Double,
        var size: SIZE? = null,
        val ingredientes: List<IngredienteDTO> = emptyList(),
        val tipo: TIPO
    )
