package com.andrea.compose_pizzeria

//import com.andrea.compose_pizzeria.ui.theme.ComposepizzeriaTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andrea.compose_pizzeria.ui.registro.PantallaInicioRegistro
import com.andrea.compose_pizzeria.ui.registro.RegistroViewModel
import com.example.compose.Mitema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       
        setContent {
           /* ComposepizzeriaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }*/
            Mitema { PantallaInicioRegistro(RegistroViewModel()) }
        }
    }
}
