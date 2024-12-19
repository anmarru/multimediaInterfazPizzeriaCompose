package com.andrea.compose_pizzeria

//import com.andrea.compose_pizzeria.ui.theme.ComposepizzeriaTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.andrea.compose_pizzer.RegistroViewModel
import com.andrea.compose_pizzeria.navigation.AppNavigation
import com.andrea.compose_pizzeria.ui.home.Home
import com.andrea.compose_pizzeria.ui.home.HomeViewModel
import com.andrea.compose_pizzeria.ui.login.LoginViewModel
import com.andrea.compose_pizzeria.ui.login.PantallaPrincipalLogin
import com.andrea.compose_pizzeria.ui.registro.PantallaInicioRegistro
import com.example.compose.Mitema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       
        setContent {

               /* Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/


            Mitema {
              // PantallaPrincipalLogin(LoginViewModel())
                //PantallaInicioRegistro(RegistroViewModel())
                //Home(HomeViewModel())
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
