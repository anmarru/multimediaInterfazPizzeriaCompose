package com.andrea.compose_pizzeria.ui.registro

import androidx.lifecycle.MutableLiveData
import com.andrea.compose_pizzeria.data.ClienteDTO


class RegistroViewModel {
    //creo mi variable registro con la clase Clientedto para tener los campos
    val cliente= MutableLiveData ( ClienteDTO(0,"","","","","","", listOf()))
}