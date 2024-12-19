package com.andrea.compose_pizzeria.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrea.compose_pizzeria.data.model.ClienteDTO
import com.andrea.compose_pizzeria.data.model.LoginDTO
import com.andrea.compose_pizzeria.data.repositories.ClienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel (private val clienteRepository: ClienteRepository) : ViewModel(){
//variable de la clse para tener sus valores
val cliente = MutableLiveData(LoginDTO())

//boolean que dice si esta logueado correctamente
val logeado= MutableLiveData(false)

//variable para boton de login habilitado
val botonHabilitado= MutableLiveData(false)

//variables de error para validar los campos
val errorEmail: MutableLiveData<String?> = MutableLiveData<String?>("")
val errorPassword: MutableLiveData<String?> = MutableLiveData<String?>("")

//funcion que se llama cuando se modifica el cliente
fun onClienteChange(newcliente: LoginDTO){
    cliente.value= newcliente
    validarCampos(newcliente)

    //funcion para hablitar el boton de inicio de sesion
    botonHabilitado.value= when{
        //isBlank hace q no tome los espacios como caracteres
        newcliente.email.isBlank()-> false
        newcliente.password.isBlank()-> false
        errorEmail.value !=null -> false
        errorPassword.value !=null-> false
        else->true
    }
}

//fincion que validara
private fun validarBotonHabilitado(cliente: ClienteDTO): Boolean {
    return cliente.email.isNotBlank() &&
            cliente.password.isNotBlank() &&
            errorEmail.value == null &&
            errorPassword.value == null
}

//funcion para login del cliente
fun onLogCliente(cliente: LoginDTO){
    Log.d("Login ", "Cliente logeado:  $cliente")
}

//funcion que valida los datos de email y contraseña
private fun validarCampos(cliente: LoginDTO){
    //valido el email
    errorEmail.value= if(cliente.email.isNotEmpty() && !cliente.email.matches(Regex("^[\\w-.]+@[\\w-]+\\.[a-z]{2,3}$"))){
        "Formato de email no valido"
    }else null

    //valido contraseña
    errorPassword.value= if(cliente.password.isNotEmpty() && cliente.password.length<4){
        "La contraseña debe tener al menos 4 caracteres"
    }else null
}

fun onLoginClick(respuesta: (Boolean)-> Unit) {
    logeado.value= true
    //realizar el login
    val clienteDTO = cliente.value
    if (clienteDTO !=null) {
        viewModelScope.launch {
            val result = clienteRepository.logearCliente(clienteDTO)
            withContext(Dispatchers.Main) {
                logeado.value = false
                when (result.isSuccess) {
                    true -> {
                        Log.d("LOGIN", "Inicio de sesión exitoso: ${cliente.value}")
                        respuesta(true)
                    }

                    false -> {
                        //logeado.value= false
                        Log.d("LOGIN", "Error al iniciar sesión: ${result.exceptionOrNull()}")
                        errorEmail.value = "Email inválido"
                        errorPassword.value = "Contraseña inválida"
                        respuesta(false)
                    }
                }
            }
        }
    }
}


}

