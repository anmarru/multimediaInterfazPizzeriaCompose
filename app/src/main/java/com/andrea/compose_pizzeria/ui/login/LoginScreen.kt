package com.andrea.compose_pizzeria.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrea.compose_pizzeria.R
import com.andrea.compose_pizzeria.data.ClienteDTO
import com.andrea.compose_pizzeria.ui.componentes.EmailTextField
import com.andrea.compose_pizzeria.ui.registro.TextoCentrado


@Composable
fun PantallaPrincipalLogin(viewModel: LoginViewModel =LoginViewModel()) {

   val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO())

    val botonHabilitado: Boolean by viewModel.botonHabilitado.observeAsState(false)

    val errorEmail by viewModel.errorEmail.observeAsState()
    val errorPassword by viewModel.errorPassword.observeAsState()

    //variable para color del OutlinedTextField
    val colorMarron= Color(0xDC573E36)

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
        .background(Color(160, 120, 90, 255))
        .padding(15.dp,20.dp,15.dp,8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(R.drawable.logopizzeria),
                contentDescription = "logo"
            )

            //pone el nombre del textField en la parte superior
            /*OutlinedTextField(
                value= cliente.email,
                //en esta variable llama a mi pantalla principal(viewModel) y llamo al metodo de loginViewModel (onValueChange)
                //escucha el cambio crea una copia y actualiza el campo en concreto (en este caso email)
                onValueChange = {viewModel.onClienteChange(cliente.copy(email = it))},
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorMarron, shape = RoundedCornerShape(5.dp))
                    .padding(top = 16.dp)
            )*/
            //funcion para el email
            EmailTextField(cliente, viewModel::onClienteChange)
            //error email
            errorEmail?.let {
                Text(
                    text = it,
                    color= MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }


            //variable ver contraseña false por defecto

            var showPassword by remember { mutableStateOf(value =false) }
            OutlinedTextField(
                value = cliente.password,
                onValueChange = {viewModel.onClienteChange(cliente.copy(password = it))},
                label= {Text("Password")},
                modifier = Modifier.fillMaxSize()
                    .background(colorMarron, shape= RoundedCornerShape(5.dp))
                    .padding(top= 16.dp),
                //condicion para ver el password
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
            //error de password
            errorPassword?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top= 8.dp)
                )
            }

            Button(onClick = {
                viewModel.onLogCliente(cliente)
            },
                modifier = Modifier
                    .padding(start = 16.dp)
                .fillMaxWidth(),
                //el enable hace que no se habilite si esta en false
                enabled = botonHabilitado

            ) {
                TextoCentrado(text="Iniciar sesión")

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPreview(){
    PantallaPrincipalLogin(LoginViewModel())
}