package com.andrea.compose_pizzeria.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.andrea.compose_pizzeria.data.ClienteDTO


@Composable
fun EmailTextField(cliente: ClienteDTO, onClienteChange: (ClienteDTO) -> Unit) {
    val colorMarron= Color(0xDC573E36)
    OutlinedTextField(
        value= cliente.email,
        //en esta variable llama a mi pantalla principal(viewModel) y llamo al metodo de loginViewModel (onValueChange)
        //escucha el cambio crea una copia y actualiza el campo en concreto (en este caso email)
        onValueChange = {onClienteChange(cliente.copy(email = it))},
        label = { Text("Email") },
        modifier = Modifier
            .fillMaxWidth()
            //.background(colorMarron, shape = RoundedCornerShape(5.dp))
            .padding(top = 16.dp)
    )
}

