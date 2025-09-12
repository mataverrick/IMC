package com.example.imc

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel: ViewModel() {
    private val _uiAppState  = MutableStateFlow(AppUiState())
    val appUiState :StateFlow <AppUiState> = _uiAppState.asStateFlow()

    fun onAlturaChange(value: String){
        _uiAppState.update { it.copy(altura = value) }
    }

    fun onPesoChange(value: String){
        _uiAppState.update { it.copy(peso = value) }
    }

    fun resetApp(){
        _uiAppState.update { it.copy(
            altura = "",
            peso = ""
        ) }
    }

    init {
        resetApp()
    }
}