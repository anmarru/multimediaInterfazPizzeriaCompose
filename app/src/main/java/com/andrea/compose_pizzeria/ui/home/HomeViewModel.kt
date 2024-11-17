package com.andrea.compose_pizzeria.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.andrea.compose_pizzeria.R
import com.andrea.compose_pizzeria.data.IngredienteDTO
import com.andrea.compose_pizzeria.data.LineaPedidoDTO
import com.andrea.compose_pizzeria.data.ProductoDTO
import com.andrea.compose_pizzeria.data.SIZE
import com.andrea.compose_pizzeria.data.TIPO

class HomeViewModel {
    //creo mi variable de producto
    val productos: MutableLiveData<List<ProductoDTO>> = MutableLiveData(listOf())

    //esta variable cambiara segun la lista de producto
    var PRODUCTOS: List<ProductoDTO> = listOf()

    //variable para almacenar la lista de lineas de pedido y me permite ser observada para q se actualice
    var cesta: MutableLiveData<List<LineaPedidoDTO>> = MutableLiveData(emptyList())

    //funcion init para cargar todos los productos
    init {
        TodosLosproductos()
    }

    //lista de productos
    private fun TodosLosproductos ()  {
       PRODUCTOS = listOf(
           ProductoDTO(nombre = "Pizza mediterránea",precio= 12.00, tipo= TIPO.PIZZA, ingredientes = listOf(
               IngredienteDTO(nombre= "Queso mozzarela", alergenos = listOf("lacteo")),
               IngredienteDTO(nombre = "Queso feta", alergenos = listOf("Lacteo")),
               IngredienteDTO(nombre = "Aceitunas negras"),
               IngredienteDTO(nombre = "Pimientos rojos"),
               IngredienteDTO(nombre = "Oregano"),
               IngredienteDTO(nombre = "Tomate cherry"),

           )),
           ProductoDTO(nombre = "Pizza trufada de champiñones",precio = 12.50, tipo = TIPO.PIZZA, ingredientes = listOf(
               IngredienteDTO(nombre = "Salsa blanca de crema y ajo"),
               IngredienteDTO(nombre = "Queso mozarela"),
               IngredienteDTO(nombre = "Champiñones frescos"),
               IngredienteDTO(nombre = "Aceite de trufa"),
               IngredienteDTO(nombre = "Parmesano rallado"),
               IngredienteDTO(nombre = "Rúcula fresca"),
           )),
           ProductoDTO(nombre= "Pizza mexicana picante", precio = 10.00, tipo= TIPO.PIZZA, ingredientes = listOf(
               IngredienteDTO(nombre= "Salsa de tomate"),
               IngredienteDTO(nombre = "Queso mozarela"),
               IngredienteDTO(nombre = "Chorizo picante"),
               IngredienteDTO(nombre = "Jalapeños"),
               IngredienteDTO(nombre = "Pimiento verde"),
               IngredienteDTO(nombre = "Maíz dulce"),
               IngredienteDTO(nombre = "Pimientos rojos"),
               IngredienteDTO(nombre = "Cebolla"),
               IngredienteDTO(nombre = "Cilantro fresco"),
           )),
           ProductoDTO(nombre= "Fetuccine al pesto de albahaca", precio = 15.50, tipo = TIPO.PASTA, ingredientes = listOf(
               IngredienteDTO(nombre = "Salsa de pesto", alergenos = listOf("Lacteos")),
               IngredienteDTO(nombre = "Espinacas frescas"),
               IngredienteDTO(nombre = "Frutos secos", alergenos = listOf("Piñones"))
           )),
           ProductoDTO(nombre = "Espagueti a la carbonara", precio = 13.00, tipo = TIPO.PASTA, ingredientes = listOf(
               IngredienteDTO(nombre = "Panceta"),
               IngredienteDTO(nombre = "Yema de huevo"),
               IngredienteDTO(nombre = "Queso pecorino"),
               IngredienteDTO(nombre = "Calabacín en rodajas"),
               IngredienteDTO(nombre = "Pimienta negra")
           )),
           ProductoDTO(nombre = "Raviolis de ricota", precio = 12.80, tipo = TIPO.PASTA, ingredientes = listOf(
               IngredienteDTO(nombre = "Ravilis rellenos de ricota"),
               IngredienteDTO(nombre = "Salsa de tomate fresco"),
               IngredienteDTO(nombre = "Ajo"),
               IngredienteDTO(nombre = "Albahaca"),
           )),
           ProductoDTO(nombre= "Agua", precio = 1.20, tipo = TIPO.BEBIDA),
           ProductoDTO(nombre= "Cerveza", precio = 1.80, tipo = TIPO.BEBIDA),
           ProductoDTO(nombre= "Cocacola", precio = 1.50, tipo = TIPO.BEBIDA),
           )

        productos.postValue(PRODUCTOS)
    }

    //funcion imagenes de los productos segunsu nombre
    fun imagenesProducto(producto: ProductoDTO) = when (producto.nombre){
        "Pizza mediterránea" -> R.drawable.pizza1
        "Pizza trufada de champiñones"-> R.drawable.pizza2
        "Pizza mexicana picante" -> R.drawable.pizza3
        "Fetuccine al pesto de albahaca"-> R.drawable.pastas1
        "Espagueti a la carbonara"-> R.drawable.pastas1
        "Raviolis de ricota"-> R.drawable.pastas1
        "Agua"-> R.drawable.bebida2
        "Cerveza" -> R.drawable.bebida3
        "Cocacola" -> R.drawable.bebida1
        else -> R.drawable.error
    }

    //funcion para añadir lineapedido
    fun agregarACesta(producto: ProductoDTO, cantidad: Int, size: SIZE?){

               val lineaPedido= LineaPedidoDTO(
                   id = producto.id,
                   cantidad = cantidad,
                   producto = producto,
                   size = size
               )
        Log.d("HomeViewModel", lineaPedido.toString() )
        cesta.value = cesta.value?.plus(lineaPedido)
    }

}