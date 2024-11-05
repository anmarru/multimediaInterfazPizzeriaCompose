package com.andrea.compose_pizzeria.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andrea.compose_pizzeria.data.ProductoDTO

@Composable
fun Home(viewModel: HomeViewModel ){
    //observador de los productos que estan en una lista, se inicia vacia
    val productos: List<ProductoDTO> by viewModel.productos.observeAsState(initial = emptyList())

    LazyColumn (
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
            .padding(15.dp,20.dp,15.dp,8.dp),
        horizontalAlignment =Alignment.CenterHorizontally
    ){
        //funciones para cada tipo de producto
       /* item {
            Image(

            )
            Text(text = "Pizzas")
        }
        items(pizza-> productoItem(pizza))*/

    }

    @Composable
    fun productoItem( productoDTO: ProductoDTO, float: Float){

    }

}