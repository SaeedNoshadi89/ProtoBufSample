package com.sn.protobufsample

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val protoRepository = ProtoRepository(application)

    var protoFirstName by mutableStateOf("empty")
        private set

    var protoLastName by mutableStateOf("empty")
        private set

    var protoAge by mutableStateOf(0)
        private set

    init {
        protoValues()
    }

    private fun protoValues() {
        viewModelScope.launch {
            protoRepository.readProto.collect {
                protoFirstName = it.firstName
                protoLastName = it.lastName
                protoAge = it.age
            }
        }
    }

    suspend fun updateProtoValues(firstName: String, lastName: String, age: Int) {
            protoRepository.updateValue(
                newFirstName = firstName,
                newLastName = lastName,
                newAge = age
            )

    }
}