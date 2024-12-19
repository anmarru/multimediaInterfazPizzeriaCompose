            package com.andrea.compose_pizzeria.ui.home

            import android.widget.Toast
            import androidx.compose.foundation.Image
            import androidx.compose.foundation.background
            import androidx.compose.foundation.layout.Arrangement
            import androidx.compose.foundation.layout.Box
            import androidx.compose.foundation.layout.Column
            import androidx.compose.foundation.layout.PaddingValues
            import androidx.compose.foundation.layout.Row
            import androidx.compose.foundation.layout.fillMaxHeight
            import androidx.compose.foundation.layout.fillMaxSize
            import androidx.compose.foundation.layout.fillMaxWidth
            import androidx.compose.foundation.layout.padding
            import androidx.compose.foundation.layout.size
            import androidx.compose.foundation.layout.width
            import androidx.compose.foundation.layout.wrapContentSize
            import androidx.compose.foundation.lazy.LazyColumn
            import androidx.compose.foundation.shape.RoundedCornerShape
            import androidx.compose.material.icons.Icons
            import androidx.compose.material.icons.filled.Menu
            import androidx.compose.material.icons.filled.ShoppingCart
            import androidx.compose.material3.AlertDialog
            import androidx.compose.material3.Badge
            import androidx.compose.material3.BadgedBox
            import androidx.compose.material3.Button
            import androidx.compose.material3.CardDefaults
            import androidx.compose.material3.CircularProgressIndicator
            import androidx.compose.material3.DrawerState
            import androidx.compose.material3.DrawerValue
            import androidx.compose.material3.DropdownMenu
            import androidx.compose.material3.DropdownMenuItem
            import androidx.compose.material3.ExperimentalMaterial3Api
            import androidx.compose.material3.Icon
            import androidx.compose.material3.IconButton
            import androidx.compose.material3.MaterialTheme
            import androidx.compose.material3.ModalNavigationDrawer
            import androidx.compose.material3.OutlinedCard
            import androidx.compose.material3.Text
            import androidx.compose.material3.TextButton
            import androidx.compose.material3.TopAppBar
            import androidx.compose.material3.rememberDrawerState
            import androidx.compose.runtime.Composable
            import androidx.compose.runtime.getValue
            import androidx.compose.runtime.livedata.observeAsState
            import androidx.compose.runtime.mutableStateOf
            import androidx.compose.runtime.remember
            import androidx.compose.runtime.rememberCoroutineScope
            import androidx.compose.runtime.setValue
            import androidx.compose.ui.Alignment
            import androidx.compose.ui.Modifier
            import androidx.compose.ui.draw.clip
            import androidx.compose.ui.draw.scale
            import androidx.compose.ui.graphics.Color
            import androidx.compose.ui.platform.LocalContext
            import androidx.compose.ui.res.painterResource
            import androidx.compose.ui.res.stringResource
            import androidx.compose.ui.text.font.FontWeight
            import androidx.compose.ui.tooling.preview.Preview
            import androidx.compose.ui.unit.dp
            import androidx.compose.ui.unit.sp
            import androidx.navigation.NavController
            import com.andrea.compose_pizzeria.R
            import com.andrea.compose_pizzeria.data.model.LineaPedidoDTO
            import com.andrea.compose_pizzeria.data.model.ProductoDTO
            import com.andrea.compose_pizzeria.data.model.SIZE
            import com.andrea.compose_pizzeria.data.model.TIPO
            import com.andrea.compose_pizzeria.navigation.Screen
            import com.example.compose.Mitema
            import kotlinx.coroutines.launch

            @OptIn(ExperimentalMaterial3Api::class)
            @Composable
            fun Home(viewModel: HomeViewModel, navController: NavController) {
            //observo la lista de productos que estan en la cesta, puede estar vacia
            val productosCesta: List<LineaPedidoDTO> by viewModel.cesta.observeAsState(initial = emptyList())

            //calculo la suma de todos los productos en el carrito
            val totalProductos = productosCesta.sumOf { it.cantidad }

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()// variable corutine para la funcion ShowLogoutDialog que muestra el dialogo
            val mostrarDialogo = remember { mutableStateOf(false) } //estado para controlar el diálogo

            val logueado: Boolean by viewModel.logueado.observeAsState(false)

            ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
            Box(
                modifier = Modifier.fillMaxHeight()
                    .width(200.dp)
            ){
                Drawer(navController, drawerState){
                    mostrarDialogo.value= true
                }
            }

            }
            ) {
            Column {
            TopAppBar(
                title = { Text(text = "Pizzería Italia con sabor") },

                navigationIcon = {
                        IconButton(onClick = {scope.launch { drawerState.open() }}) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                },
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

            val productos by viewModel.productos.observeAsState(listOf())// esta variable llamara a los productos para que los llame desde la api
            //para que todos los productos se puedan ver hacia abajo
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(bottom = 50.dp)
            ) {

                item    {
                    Image(
                        painter = painterResource(id = R.drawable.logopizzeria),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                //lista de productos: llamo a la variable PRODUCTOS DE HomeViewModel para que me de los productos segun el tipo de producto
                item {
                    ProductoItem(
                        "Pastas",
                        productos.filter { it.tipo == TIPO.pasta },
                        viewModel
                    )
                }
                item {
                    ProductoItem(
                        "Pizzas",
                        productos.filter { it.tipo == TIPO.pizza },
                        viewModel
                    )
                }
                item {
                    ProductoItem(
                        "Bebidas",
                        productos.filter { it.tipo == TIPO.bebida },
                        viewModel
                    )
                }
                /*item {
                    Button(onClick = {navController.navigate(Screen.Login.route)}) {//al cerrar sesion lleva al login
                        Text(
                            text = "Cerrar sesión"
                        )
                    }
                }*/
            }

            }
            //muestro el dialogo si esta activado llamando a la variable antes definida
            if (mostrarDialogo.value) {
            ShowLogoutDialog(
                alConfirmar = {
                    mostrarDialogo.value = false//cierra el dialogo
                    navController.navigate(Screen.Login.route)//navega al login
                },
                alCancelar = { mostrarDialogo.value = false }//solo cierra el dialogo
            )
            }
            }
            }

            //creo la funcion que tiene el panel lateral que recibe un navControler para navegar a las ventana
            //el drawerState controlara si el drawer esta abierto o cerrado
            //lambda alCerrarSesion activa el dialogo
            @Composable
            fun Drawer(navController: NavController, drawerState: DrawerState, alCerrarSesion: () -> Unit) {
            val scope = rememberCoroutineScope()

            Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
            TextButton(
            onClick = {
                scope.launch { drawerState.close() }
                navController.navigate(Screen.Home.route)
            }
            ) {
            Text("Home")
            }
            TextButton(
            onClick = {
                scope.launch { drawerState.close() }
                alCerrarSesion() //activa el dialogo
            }
            ) {
            Text("Cerrar sesión")
            }
            }
            }
            //funcion para mostrar el dialogo
            @Composable
            fun ShowLogoutDialog(alConfirmar: () -> Unit, alCancelar: () -> Unit) {
            AlertDialog(
            onDismissRequest = alCancelar,
            title = { Text("Cerrar sesión") },
            text = { Text("¿Está seguro de que desea cerrar sesión?") },
            confirmButton = {
            TextButton(onClick = alConfirmar) {
                Text("Sí")
            }
            },
            dismissButton = {
            TextButton(onClick = alCancelar) {
                Text("No")
            }
            }
            )
            }
            //funcion que enseña los productos en una carta con mas funcionalidades
            @Composable
            fun TarjetadeProductos(producto: ProductoDTO, viewModel: HomeViewModel) {


            OutlinedCard(
            modifier = Modifier
            .padding(8.dp),
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
                color =  Color(180, 152, 61, 255),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            //variable para agregar las imagenes
            val imagenProducto = viewModel.imagenesProducto(producto)

            Row {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(id = imagenProducto),
                        contentDescription = producto.nombre,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                }

                //si los productos son distintos de bebida me enseña los ingredientes
                if (producto.tipo != TIPO.bebida) {

                    //agrego el texto que tiene los ingredientes
                    Text(
                        text = producto.ingredientes.joinToString { it.nombre } ?: "",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(8.dp)
                    )

                }


            }

            //variable para seleccionar el tamaño
            var seleccionar: SIZE? by remember { mutableStateOf(producto.size) }
            //creo el menu desplegable para el tamaño
            var expandir by remember { mutableStateOf(false) }//variable para expandir el menu
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
                ){
                Text(
                    text = "${producto.precio}€",
                    //estilo para el precio
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Red, //color mas resaltado
                        fontSize = 20.sp // Aumentar el tamaño de la fuente
                    ),
                    modifier = Modifier.padding(8.dp)
                )

                if (producto.tipo == TIPO.pizza || producto.tipo == TIPO.bebida) {
                    Box(modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)) {
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
                                    seleccionar = SIZE.MEDIANA
                                    expandir = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Pequeña") },
                                onClick = {
                                    seleccionar = SIZE.PEQUENA
                                    expandir = false
                                }
                            )
                        }

                    }

                }
            }


            //para poner los botones de + y - del producto
            var contador by remember { mutableStateOf(1) }//variable contador

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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
                TextButton(
                    onClick = { contador++ },
                    modifier = Modifier.scale(1f)
                ) {

                    Text("+")
                }
                //boton para añadir el producto a la cesta
                CarritoAñadir(contador, viewModel, producto, seleccionar)

            }
            }


            }
            }


            //METODO PARA MOSTRAR LOS PRODUCTOS EN LAS TARJETAS (usando la fun tarjetadeproductos)
            @Composable
            fun ProductoItem(nombreDeProducto: String, productos: List<ProductoDTO>, viewModel: HomeViewModel) {
            Text(
            text = nombreDeProducto,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
            )
            Column {
            //muestro el progreso mientras cargan los ingerdientes con un circularPro
            if (productos.isEmpty()) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                if(productos.isEmpty()){
                    CircularProgressIndicator(modifier = Modifier.padding(40.dp))
                }
            }
            } else {
            productos.forEach { producto -> TarjetadeProductos(producto, viewModel) }
            }

            }
            }

            //FUNCION PARA EL BOTON AÑADIR
            @Composable
            fun CarritoAñadir(contador: Int, viewModel: HomeViewModel, producto: ProductoDTO, seleccionar: SIZE?) {
            //variable para enseñar mensaje añadido producto
            val contexto = LocalContext.current
            //boton para añadir el producto a la cesta
            IconButton(
            onClick = {
            //llamo a mi funcion agregarACesta de HomeViewModel
            if (contador > 0) {
                viewModel.agregarACesta(producto, contador, seleccionar)

                //muestro mensaje de producto añadido
                Toast.makeText(
                    contexto, "Se han añadido $contador ${producto.nombre}", Toast.LENGTH_SHORT
                ).show()
            }
            },
            //modifier = Modifier.align(Alignment.CenterHorizontally),
            /*si el tamaño en pizza o en bebida no esta seleccionado no se habilita el boton para añadir
            * o si es tipo pasta si se hablilita
            */
            enabled = seleccionar != null || producto.tipo == TIPO.pasta

            ) {
            Icon(
            imageVector = Icons.Default.ShoppingCart,

            contentDescription = "Añadir a la cesta"
            )
            }
            }



            @Preview(showBackground = true)
            @Composable
            fun PantallaPreview() {
            Mitema {
            //Home(HomeViewModel(), rememberNavController())
            }
            }

