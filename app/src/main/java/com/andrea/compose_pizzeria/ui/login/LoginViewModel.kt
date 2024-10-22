package com.andrea.compose_pizzeria.ui.login

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){
    val texto= MutableLiveData("")

    fun onTextoChange(newTexto: String){
        texto.value= newTexto
    }
}