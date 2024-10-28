package com.andrea.compose_pizzer

import androidx.lifecycle.MutableLiveData
import com.andrea.compose_pizzeria.data.ClienteDTO


class RegistroViewModel {
    //creo mi variable registro con la clase Clientedto para tener los campos
    val cliente: MutableLiveData<ClienteDTO> = MutableLiveData ( ClienteDTO())

    //variable para boton de registro habilitado
    val botonHabilitado= MutableLiveData(false)

    //variable de la clase errormensaje para validar los campos
    //val errorMensaje= MutableLiveData(ErrorMensaje())

    //variables de error para los campos
    val errorNombre: MutableLiveData<String?> = MutableLiveData<String?>("")
    val errorEmail: MutableLiveData<String?> = MutableLiveData<String?>("")
    val errorPassword: MutableLiveData<String?> = MutableLiveData<String?>("")

    //funcion para que cree un nuevo cliente actualizado con los valores y se usa en el RegistroScreen
    fun onClienteChange(newCliente: ClienteDTO){
        cliente.value=newCliente
        //errorMensaje.value = ErrorMensaje(cliente.nombre, cliente.value.email, cliente.value.password)

        //valido los campos
        validarCampos(newCliente)
        //continuacion, funcion habilitar el boton de registro
        botonHabilitado.value = when {
            //isBlank hace q no tome los espacios como caracteres mejor q empty
            newCliente.dni.isBlank() -> false
            newCliente.nombre.isBlank() -> false
            newCliente.email.isBlank() -> false
            newCliente.telefono.isBlank() -> false
            newCliente.direccion.isBlank() -> false
            newCliente.password.isBlank() -> false
            !(errorEmail.value==null) -> false
           !( errorNombre.value== null)-> false
            !(errorPassword.value== null)-> false
            else ->  true
        }

    }

    //creo la funcion que valida los campos y verifica si son validos
    private fun validarCampos(cliente: ClienteDTO){
        //valido el nombre
        errorNombre.value= if (cliente.nombre.any{it.isDigit()}){
            "El nombre no debe contener digitos"
        }else null

        //valido email
        errorEmail.value = if (cliente.email.isNotEmpty()&&!cliente.email.matches(Regex("^[\\w-.]+@[\\w-]+\\.[a-z]{2,3}$"))) {
            "Formato de email inválido"
        }else null

        //si el password no esta vacio y tiene mas caracteres
        errorPassword.value= if (cliente.password.isNotEmpty()&& cliente.password.length< 4){
            "La contraseña debe tener al menos 4 caracteres"
        }else null
    }



}