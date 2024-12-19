package com.andrea.compose_pizzeria.ui.registro

import androidx.lifecycle.MutableLiveData
import com.andrea.compose_pizzeria.data.model.ClienteDTO

data class ErrorMensaje (
    val nombre : String?="",
    val email: String?="",
    val password: String?="",

    //variables de error para los campos
    val errorNombre: MutableLiveData<String?> = MutableLiveData<String?>(""),
    val errorEmail: MutableLiveData<String?> = MutableLiveData<String?>(""),
    val errorPassword: MutableLiveData<String?> = MutableLiveData<String?>("")
) {
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





