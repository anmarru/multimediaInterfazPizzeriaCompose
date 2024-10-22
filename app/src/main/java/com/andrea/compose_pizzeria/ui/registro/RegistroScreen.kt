package com.andrea.compose_pizzeria.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andrea.compose_pizzeria.R
import com.andrea.compose_pizzeria.data.ClienteDTO
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.andrea.compose_pizzeria.ui.login.PantallaPrincipal

@Composable
fun PantallaInicioRegistro(viewModel: RegistroViewModel= RegistroViewModel()){

    val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO(0,"","","","","","", listOf()))

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize().padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally

        ) {
        item {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(R.drawable.logopizzeria),
                contentDescription = "logo"
            )
            /*Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )*/
            
            TextField(
                
                value= cliente.dni,
                onValueChange = {},
                label = { Text("DNI") }
                
            )
            TextField(

                value= cliente.direccion,
                onValueChange = {},
                label = { Text("Dirección") }

            )
            TextField(

                value= cliente.telefono,
                onValueChange = {},
                label = { Text("Telefono") }

            )
            TextField(

                value= cliente.email,
                onValueChange = {},
                label = { Text("Email") }

            )
            TextField(

                value= cliente.nombre,
                onValueChange = {},
                label = { Text("Nombre") }

            )
            TextField(

                value= cliente.password,
                onValueChange = {},
                label = { Text("Password") }
            )
            Button(onClick = {},
                modifier = Modifier
                    .padding(start = 8.dp)) {
                TextoCentrado(text="Registrarse")
            }
        }

    }
}

//fun para centrar mis textos
@Composable
fun TextoCentrado(text: String){
    Column (
        modifier= Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center, //centro verticalmente
        horizontalAlignment = Alignment.CenterHorizontally//centro horizontalmente
    ){
        Text(
            text=text,
            textAlign = TextAlign.Center
        )
    }
}


//previsualizo pantalla registro
@Preview(showBackground = true)
@Composable
fun PantallaPreview(){
    PantallaInicioRegistro(RegistroViewModel())
}