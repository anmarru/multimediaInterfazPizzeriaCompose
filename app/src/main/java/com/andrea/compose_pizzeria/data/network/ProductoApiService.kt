    package com.andrea.compose_pizzeria.data.network


    import com.andrea.compose_pizzeria.data.model.ProductoDTO
    import retrofit2.Response
    import retrofit2.http.GET

    interface ProductoApiService {
        @GET("/productos")
        suspend fun getProductos(): List<ProductoDTO>
    }