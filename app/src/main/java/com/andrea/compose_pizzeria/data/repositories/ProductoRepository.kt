package com.andrea.compose_pizzeria.data.repositories

import com.andrea.compose_pizzeria.data.model.ProductoDTO
import com.andrea.compose_pizzeria.data.network.ProductoApiService

class ProductoRepository (private val apiService: ProductoApiService){
suspend fun obtenerProductos() : Result<List<ProductoDTO>>{
return try {
    val response = apiService.getProductos()
    Result.success(response)
}catch (e: Exception){
    Result.failure(e)
}

}
}