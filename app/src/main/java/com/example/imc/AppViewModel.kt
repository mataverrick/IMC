package com.example.imc

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel : ViewModel() {
    private val _uiAppState = MutableStateFlow(AppUiState())

    val appUiState: StateFlow<AppUiState> = _uiAppState.asStateFlow()

    fun onAlturaChange(value: String) {
        _uiAppState.update { it.copy(altura = value) }
    }

    fun onPesoChange(value: String) {
        _uiAppState.update { it.copy(peso = value) }
    }

    fun calcularImc() {
        val peso: Double? = _uiAppState.value.peso.toDoubleOrNull()
        val altura: Double? = _uiAppState.value.altura.toDoubleOrNull()

        if (peso == null || altura == null) return

        val imc: Double = peso / (altura * altura)

        val result: String? = when {
            (imc < 18.5) -> "Su imc es de $imc. Este $imc corresponde a un bajo peso"
            (imc in 18.5..24.9) -> "Su imc es de $imc. Este imc corresponde a peso normal."
            (imc in 25.0..29.9) -> "Su imc es de $imc. Este imc corresponde a sobrepeso"
            (imc in 30.0..34.9) -> "Su imc es de $imc. Este imc corresponde a obesidad tipo 1."
            (imc in 35.0..39.9) -> "Su imc es de $imc. Este imc corresponde a obesidad tipo 2."
            (imc >= 40) -> "Su imc es de $imc . Este imc corresponde a obesidad tipo 3."
            else -> null
        }

        _uiAppState.update { it.copy(imc = result) }
    }

    fun isError(): Boolean{
        if(_uiAppState.value.peso.isBlank() || _uiAppState.value.altura.isBlank()) return false


        return true
    }



    fun resetApp() {
        _uiAppState.update {
            it.copy(
                imc = null,
                altura = "",
                peso = ""
            )
        }
    }

    init {
        resetApp()
    }
}