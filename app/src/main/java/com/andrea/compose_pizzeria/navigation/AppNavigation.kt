package com.andrea.compose_pizzeria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andrea.compose_pizzer.RegistroViewModel
import com.andrea.compose_pizzeria.data.network.RetrofitInstance
import com.andrea.compose_pizzeria.data.repositories.ClienteRepository
import com.andrea.compose_pizzeria.data.repositories.ProductoRepository
import com.andrea.compose_pizzeria.ui.home.Home
import com.andrea.compose_pizzeria.ui.home.HomeViewModel
import com.andrea.compose_pizzeria.ui.login.LoginViewModel
import com.andrea.compose_pizzeria.ui.login.PantallaPrincipalLogin
import com.andrea.compose_pizzeria.ui.registro.PantallaInicioRegistro


@Composable
fun AppNavigation(navController: NavHostController) {
val api = RetrofitInstance.clienteApi
val productoApi = RetrofitInstance.productoApi
val clienteRepository: ClienteRepository = ClienteRepository(api)

val productoRepository : ProductoRepository = ProductoRepository(productoApi)

NavHost(
    navController = navController,
    startDestination = Screen.Login.route
) {
    composable(Screen.Login.route){
        PantallaPrincipalLogin(LoginViewModel(clienteRepository), navController = navController)
    }
    composable(Screen.Registro.route) {
         PantallaInicioRegistro(RegistroViewModel(clienteRepository),
             navController = navController)
    }
    composable(Screen.Home.route) {
       Home(HomeViewModel(productoRepository),
           navController = navController)
    }

}



}