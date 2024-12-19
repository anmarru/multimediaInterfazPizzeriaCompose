package com.andrea.compose_pizzeria.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrea.compose_pizzeria.R
import com.andrea.compose_pizzeria.data.model.IngredienteDTO
import com.andrea.compose_pizzeria.data.model.LineaPedidoDTO
import com.andrea.compose_pizzeria.data.model.ProductoDTO
import com.andrea.compose_pizzeria.data.model.SIZE
import com.andrea.compose_pizzeria.data.model.TIPO
import com.andrea.compose_pizzeria.data.repositories.ProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//se crea una variable de clienteReposirory
class HomeViewModel(val productoRepository: ProductoRepository) : ViewModel() {
//creo mi variable de producto
val productos: MutableLiveData<List<ProductoDTO>> = MutableLiveData(listOf())

//esta variable cambiara segun la lista de producto
var PRODUCTOS: List<ProductoDTO> = listOf()

//variable para almacenar la lista de lineas de pedido y me permite ser observada para q se actualice
var cesta: MutableLiveData<List<LineaPedidoDTO>> = MutableLiveData(emptyList())

val logueado= MutableLiveData(false)

//funcion init para cargar todos los productos
init {
   // TodosLosproductos()

    viewModelScope.launch {
        val result = productoRepository.obtenerProductos()
        withContext(Dispatchers.Main) {
            when (result.isSuccess) {
                true -> {
                    PRODUCTOS = result.getOrThrow()
                    productos.postValue(PRODUCTOS)
                    Log.d("HOME", "Productos : $PRODUCTOS")
                }
                false -> {
                    Log.d("HOME", "Error al cargar productos: ${result.exceptionOrNull()}")
                }
            }
        }
    }
}

//lista de productos
private fun TodosLosproductos ()  {
   PRODUCTOS = listOf(
       ProductoDTO(nombre = "Pizza mediterránea",precio= 12.00, tipo= TIPO.pizza, ingredientes = listOf(
           IngredienteDTO(nombre= "Queso mozzarela", alergenos = listOf("lacteo")),
           IngredienteDTO(nombre = "Queso feta", alergenos = listOf("Lacteo")),
           IngredienteDTO(nombre = "Aceitunas negras"),
           IngredienteDTO(nombre = "Pimientos rojos"),
           IngredienteDTO(nombre = "Oregano"),
           IngredienteDTO(nombre = "Tomate cherry"),

       )),
       ProductoDTO(nombre = "Pizza trufada de champiñones",precio = 12.50, tipo = TIPO.pizza, ingredientes = listOf(
           IngredienteDTO(nombre = "Salsa blanca de crema y ajo"),
           IngredienteDTO(nombre = "Queso mozarela"),
           IngredienteDTO(nombre = "Champiñones frescos"),
           IngredienteDTO(nombre = "Aceite de trufa"),
           IngredienteDTO(nombre = "Parmesano rallado"),
           IngredienteDTO(nombre = "Rúcula fresca"),
       )),
       ProductoDTO(nombre= "Pizza mexicana picante", precio = 10.00, tipo= TIPO.pizza, ingredientes = listOf(
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
       ProductoDTO(nombre= "Fetuccine al pesto de albahaca", precio = 15.50, tipo = TIPO.pasta, ingredientes = listOf(
           IngredienteDTO(nombre = "Salsa de pesto", alergenos = listOf("Lacteos")),
           IngredienteDTO(nombre = "Espinacas frescas"),
           IngredienteDTO(nombre = "Frutos secos", alergenos = listOf("Piñones"))
       )),
       ProductoDTO(nombre = "Espagueti a la carbonara", precio = 13.00, tipo = TIPO.pasta, ingredientes = listOf(
           IngredienteDTO(nombre = "Panceta"),
           IngredienteDTO(nombre = "Yema de huevo"),
           IngredienteDTO(nombre = "Queso pecorino"),
           IngredienteDTO(nombre = "Calabacín en rodajas"),
           IngredienteDTO(nombre = "Pimienta negra")
       )),
       ProductoDTO(nombre = "Raviolis de ricota", precio = 12.80, tipo = TIPO.pasta, ingredientes = listOf(
           IngredienteDTO(nombre = "Ravilis rellenos de ricota"),
           IngredienteDTO(nombre = "Salsa de tomate fresco"),
           IngredienteDTO(nombre = "Ajo"),
           IngredienteDTO(nombre = "Albahaca"),
       )),
       ProductoDTO(nombre= "Agua", precio = 1.20, tipo = TIPO.bebida),
       ProductoDTO(nombre= "Cerveza", precio = 1.80, tipo = TIPO.bebida),
       ProductoDTO(nombre= "Cocacola", precio = 1.50, tipo = TIPO.bebida),
       )

    productos.postValue(PRODUCTOS)
}

//funcion imagenes de los productos segunsu nombre
fun imagenesProducto(producto: ProductoDTO) = when (producto.nombre){
    "Margarita" -> R.drawable.pizza1
    "Pepperoni"-> R.drawable.pizza2
    "Cuatro Quesos" -> R.drawable.pizza3
    "Espagueti"-> R.drawable.pastas1
    "Pasta Alfredo"-> R.drawable.pastas1
    "Raviolis de ricota"-> R.drawable.pastas1
    "Agua Mineral"-> R.drawable.bebida2
    "Fanta Naranja" -> R.drawable.bebida3
    "Coca-Cola" -> R.drawable.bebida1
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