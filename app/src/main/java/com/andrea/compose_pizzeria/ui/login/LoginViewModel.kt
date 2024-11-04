package com.andrea.compose_pizzeria.ui.login

import android.util.Log
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrea.compose_pizzeria.data.ClienteDTO

class LoginViewModel: ViewModel(){
    //variable de la clse para tener sus valores
    val cliente= MutableLiveData(ClienteDTO())

    //variable para boton de login habilitado
    val botonHabilitado= MutableLiveData(false)

    //variables de error para validar los campos
    val errorEmail: MutableLiveData<String?> = MutableLiveData<String?>("")
    val errorPassword: MutableLiveData<String?> = MutableLiveData<String?>("")

    //funcion que se llama cuando se modifica el cliente
    fun onClienteChange(newcliente: ClienteDTO){
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

    //funcion para login del cliente
    fun onLogCliente(cliente: ClienteDTO){
        Log.d("Login ", "Cliente logeado:  $cliente")
    }
    //funcion que valida los datos de email y contraseña
    private fun validarCampos(cliente: ClienteDTO){
        //valido el email
        errorEmail.value= if(cliente.email.isNotEmpty() && !cliente.email.matches(Regex("^[\\w-.]+@[\\w-]+\\.[a-z]{2,3}$"))){
            "Formato de email no valido"
        }else null

        //valido contraseña
        errorPassword.value= if(cliente.password.isNotEmpty() && cliente.password.length<4){
            "La contraseña debe tener al menos 4 caracteres"
        }else null
    }
}

