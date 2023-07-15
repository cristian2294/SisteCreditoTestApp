package com.cristian.sistecreditotestapp.listarjuegos.presentacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.sistecreditotestapp.listarjuegos.dominio.casosdeuso.ObtenerJuegosUC
import com.cristian.sistecreditotestapp.listarjuegos.dominio.modelos.aPresentacion
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.estados.EstadoJuego
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JuegosViewModel @Inject constructor(
    private val obtenerJuegosUC: ObtenerJuegosUC,
) : ViewModel() {

    private val _estadoJuegos = MutableStateFlow<EstadoJuego>(EstadoJuego.Cargando)
    val estadoJuegos: StateFlow<EstadoJuego> get() = _estadoJuegos

    init {
        obtenerJuegos()
    }

    private fun obtenerJuegos() {
        viewModelScope.launch {
            _estadoJuegos.value = EstadoJuego.Cargando
            try {
                val juegosDominio = obtenerJuegosUC()
                val juegosPresentacion = juegosDominio.map { it.aPresentacion() }
                _estadoJuegos.value = EstadoJuego.Exitoso(juegosPresentacion)
            } catch (error: Exception) {
                _estadoJuegos.value = EstadoJuego.Error(error)
            }
        }
    }
}
