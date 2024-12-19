    package com.andrea.compose_pizzeria.data.repositories

    import com.andrea.compose_pizzeria.data.model.ClienteDTO
    import com.andrea.compose_pizzeria.data.model.LoginDTO
    import com.andrea.compose_pizzeria.data.network.ClienteApiService

    class ClienteRepository( val apiService: ClienteApiService) {
        //para registrar cliente
        suspend fun registrarCliente(cliente: ClienteDTO): Result<ClienteDTO> {
            return try {
                val response = apiService.registrarCliente(cliente)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }


        //para loguear el cliente
        suspend fun logearCliente(login: LoginDTO): Result<ClienteDTO> {
            return try {
                val response = apiService.loginCliente(login)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    }