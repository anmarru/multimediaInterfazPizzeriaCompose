package com.andrea.compose_pizzeria.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andrea.compose_pizzeria.R
import com.andrea.compose_pizzeria.data.ClienteDTO
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.andrea.compose_pizzeria.ui.login.PantallaPrincipal
import com.example.compose.backgroundDark

@Composable
fun PantallaInicioRegistro(viewModel: RegistroViewModel= RegistroViewModel()){

    val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO(0,"","","","","","", listOf()))
    //variable para color del OutlinedTextField
    val colorMarron= Color(0xDC573E36)

    //creo la variable que hace referencia al boton habilitado esta siempre escuchando
    val botonHabilitado:Boolean by viewModel.botonHabilitado.observeAsState(false)
    println(cliente.toString())



    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
            .background(Color(160, 120, 90, 255))
            .padding(15.dp,20.dp,15.dp,8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

        ) {
        item {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(R.drawable.logopizzeria),
                contentDescription = "logo"
            )
            /*Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )*/

            //pone el nombre del textField en la parte superior
            OutlinedTextField(
                
                value= cliente.dni,
                //en esta variable llama a mi pantalla principal(viewModel) y llamo al metodo de resgistroViewModel (onValueChange)
                //escucha el cambio crea una copia y actualiza el campo en concreto (en este caso dni)
                onValueChange = {viewModel.onClienteChange(cliente.copy(dni=it))},
                label = { Text("DNI") },
                modifier = Modifier.fillMaxSize()
                    .background(colorMarron, shape= RoundedCornerShape(5.dp))


                
            )
            OutlinedTextField(

                value= cliente.direccion,
                onValueChange = {viewModel.onClienteChange(cliente.copy(direccion = it))},
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxSize()
                    .background(colorMarron, shape= RoundedCornerShape(5.dp))
                    .padding(top = 5.dp)

            )
            OutlinedTextField(

                value= cliente.telefono,
                onValueChange = {viewModel.onClienteChange(cliente.copy(telefono=it))},
                label = { Text("Telefono") },
                modifier = Modifier.fillMaxSize()
                    .background(colorMarron, shape= RoundedCornerShape(5.dp))
                    .padding(top = 5.dp)


            )
            OutlinedTextField(

                value= cliente.email,
                onValueChange = {viewModel.onClienteChange(cliente.copy(email=it))},//escucha el cambio
                label = { Text("Email") },
                modifier = Modifier.fillMaxSize()
                    .background(colorMarron, shape= RoundedCornerShape(5.dp))
                    .padding(top = 5.dp)

            )
            OutlinedTextField(

                value= cliente.nombre,
                onValueChange = {viewModel.onClienteChange(cliente.copy(nombre=it))},
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxSize()
                    .background(colorMarron, shape= RoundedCornerShape(5.dp))
                   .padding(top = 5.dp)

            )
            //variable de ver el password en false por defecto
            var showPassword by remember { mutableStateOf(value = false) }
            OutlinedTextField(

                value= cliente.password,
                onValueChange = {viewModel.onClienteChange(cliente.copy(password = it))},
                label = { Text("Password") },
                modifier = Modifier.fillMaxSize()
                    .background(colorMarron, shape= RoundedCornerShape(5.dp))
                    .padding(top= 5.dp),
                //condicion para cer el password
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                }
            )
            Button(onClick = {},
                modifier = Modifier
                    .padding(start = 8.dp),
                //el enable hace que no se habilite si esta en false
                enabled = botonHabilitado

            ) {
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