    package com.andrea.compose_pizzeria.data.network

    import com.andrea.compose_pizzeria.data.model.ClienteDTO
    import com.andrea.compose_pizzeria.data.model.LoginDTO
    import retrofit2.Response
    import retrofit2.http.Body
    import retrofit2.http.GET
    import retrofit2.http.POST
    import retrofit2.http.PUT

    interface ClienteApiService {
        @POST("/clientes/registro")
        suspend fun registrarCliente(@Body cliente: ClienteDTO): ClienteDTO
        @POST("/clientes/login")
        suspend fun loginCliente(@Body loginDTO: LoginDTO): ClienteDTO
        @PUT("/clientes/actualizar")
        suspend fun actualizarCliente(@Body cliente: ClienteDTO): ClienteDTO
        @GET("/clientes")
        suspend fun getUsers(): Response<List<ClienteDTO>>
    }