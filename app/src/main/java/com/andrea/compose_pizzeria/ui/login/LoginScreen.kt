package com.andrea.compose_pizzeria.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.andrea.compose_pizzeria.R
import java.util.Objects

@Composable
fun PantallaPrincipal(viewModel: LoginViewModel =LoginViewModel()) {

    // val texto: String by viewModel.texto.observeAsState("")
    //var item: List<Any>

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize(),

    ) {
        item {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(R.drawable.logopizzeria),
                contentDescription = "logo"
            )
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPreview(){
    PantallaPrincipal(LoginViewModel())
}