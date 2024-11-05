package com.andrea.compose_pizzeria.ui.home

import androidx.lifecycle.MutableLiveData
import com.andrea.compose_pizzeria.data.ProductoDTO

class HomeViewModel {
    //creo mi variable de producto
    val productos: MutableLiveData<List<ProductoDTO>> = MutableLiveData(listOf())

    /*fun init{
        cargarProductos(

        )
    }*/
}