package com.andrea.compose_pizzeria.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andrea.compose_pizzeria.R
import com.andrea.compose_pizzeria.data.LineaPedidoDTO
import com.andrea.compose_pizzeria.data.ProductoDTO
import com.andrea.compose_pizzeria.data.SIZE
import com.andrea.compose_pizzeria.data.TIPO
import com.example.compose.Mitema

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModel: HomeViewModel ) {
    //observo la lista de productos que estan en la cesta, puede estar vacia
    val productosCesta: List<LineaPedidoDTO> by viewModel.cesta.observeAsState(initial = emptyList())

    //calculo la suma de todos los productos en el carrito
    val totalProductos = productosCesta.sumOf { it.cantidad }

    Column {
        TopAppBar(
            title = { Text(text = "Pizzería Italia con sabor") },
            actions = {
                BadgedBox(
                    badge = {
                        Badge {
                            Text(
                                //enseño el total de los productos que se añaden al carrito
                                text = "$totalProductos"
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrito"
                    )
                }
            }
        )


            //para que todos los productos se puedan ver hacia abajo
            LazyColumn(
                //verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(bottom = 50.dp)
            ) {
                //funciones para cada tipo de producto
                item {
                    Image(
                        painter = painterResource(id = R.drawable.logopizzeria),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                //llamo a la variable PRODUCTOS DE HomeViewModel para que me de los productos segun el tipo de producto
                item {
                    ProductoItem(
                        "Pastas",
                        viewModel.PRODUCTOS.filter { it.tipo == TIPO.PASTA },
                        viewModel
                    )
                }
                item {
                    ProductoItem(
                        "Pizzas",
                        viewModel.PRODUCTOS.filter { it.tipo == TIPO.PIZZA },
                        viewModel
                    )
                }
                item {
                    ProductoItem(
                        "Bebidas",
                        viewModel.PRODUCTOS.filter { it.tipo == TIPO.BEBIDA },
                        viewModel
                    )
                }
            }

    }
}
    //funcion que enseña los productos en una carta con mas funcionalidades
    @Composable
    fun TarjetadeProductos(producto: ProductoDTO, viewModel: HomeViewModel) {
        OutlinedCard(
            modifier = Modifier
                .padding(8.dp),
                //.fillMaxWidth()
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                //variable para agregar las imagenes
                val imagenProducto = viewModel.imagenesProducto(producto)
                Image(
                    painter = painterResource(id = imagenProducto),
                    contentDescription = producto.nombre,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))//borde redono
                        .align(Alignment.CenterHorizontally)
                )
                //si los productos son distintos de bebida me enseña los ingredientes
                if (producto.tipo != TIPO.BEBIDA) {

                    //agrego el texto que tiene los ingredientes
                    Text(
                        text = producto.ingredientes.joinToString { it.nombre } ?: "",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(8.dp)
                    )

                }
                //agrego el precio a todas los productos
                Text(
                    text = "${producto.precio}€",
                    //estilo para el precio
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Green, //color mas resaltado en verde
                        fontWeight = FontWeight.Bold, //texto en negrita
                        fontSize = 20.sp // Aumentar el tamaño de la fuente
                    ),
                    modifier = Modifier.padding(8.dp)
                )
                //para poner los botones de + y - del producto
                var contador by remember { mutableStateOf(0) }//variable contador
                //variable para seleccionar el tamaño
                var seleccionar by remember { mutableStateOf<SIZE?>(producto.size) }

                Row(
                    modifier = Modifier.padding(1.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = { if (contador != 1) contador-- },
                        modifier = Modifier.scale(1f)
                        ) {

                        Text("-")
                    }

                    Text(
                        text = "$contador",//enseño la cantidad del contador
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleLarge

                    )
                    TextButton(onClick = { contador++ },
                        modifier = Modifier.scale(1f)
                        ) {

                        Text("+")
                    }

                    //creo el menu desplegable para el tamaño
                    var expandir by remember { mutableStateOf(false) }//variable para expandir el menu

                    if (producto.tipo == TIPO.PIZZA || producto.tipo == TIPO.BEBIDA) {
                        Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopStart)) {
                            TextButton(onClick = { expandir = true }) {
                                Text(
                                    seleccionar?.name ?: "Seleccionar tamaño"
                                )//cuando se selecciona se muestra la opcion
                            }
                            DropdownMenu(
                                expanded = expandir,
                                onDismissRequest = {
                                    expandir = false//cuando se hace click fuera se cierra
                                }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Grande") },
                                    onClick = {
                                        seleccionar = SIZE.GRANDE
                                        expandir = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Mediana") },
                                    onClick = {
                                        seleccionar = SIZE.MEDIANO
                                        expandir = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Pequeña") },
                                    onClick = {
                                        seleccionar = SIZE.PEQUEÑO
                                        expandir = false
                                    }
                                )
                            }

                        }
                    }
                }

                //boton para añadir el producto a la cesta
                IconButton(
                    onClick = {
                        //llamo a mi funcion agregarACesta de HomeViewModel
                        if (contador > 0) {
                            viewModel.agregarACesta(producto, contador, seleccionar)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Añadir a la cesta"
                    )
                }

            }
        }
    }

//METODO PARA MOSTRAR LOS PRODUCTOS EN LAS TARJETAS (usando la fun tarjetadeproductos)
@Composable
    fun ProductoItem(nombreDeProducto: String, productos : List<ProductoDTO>, viewModel: HomeViewModel){
        Text(
            text= nombreDeProducto,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(8.dp)
        )
        Column {
            productos.forEach{producto-> TarjetadeProductos(producto, viewModel)}

        }
    }


@Preview(showBackground = true)
@Composable
fun PantallaPreview(){
    Mitema {
        Home(HomeViewModel())
    }
}

