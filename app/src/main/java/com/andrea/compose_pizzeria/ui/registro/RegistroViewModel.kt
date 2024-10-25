package com.andrea.compose_pizzeria.ui.registro

import androidx.lifecycle.MutableLiveData
import com.andrea.compose_pizzeria.data.ClienteDTO


class RegistroViewModel {
    //creo mi variable registro con la clase Clientedto para tener los campos
    val cliente= MutableLiveData ( ClienteDTO(0,"","","","","","", listOf()))

    //variable para boton de registro habilitado
    val botonHabilitado= MutableLiveData(false)

    //funcion para que cree un nuevo cliente actualizado con los valores y se usa en el RegistroScreen
    fun onClienteChange(newCliente: ClienteDTO){
        cliente.value=newCliente
        //continuacion, funcion habilitar el boton de registro
        botonHabilitado.value = when {
            //isBlank hace q no tome los espacios como caracteres mejor q empty
            newCliente.dni.isBlank() -> false
            newCliente.nombre.isBlank() -> false
            newCliente.email.isBlank() -> false
            newCliente.telefono.isBlank() -> false
            newCliente.direccion.isBlank() -> false
            newCliente.password.isBlank() -> false
            else ->  true
        }
    }


}