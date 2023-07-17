package com.cristian.sistecreditotestapp.detallejuego.presentacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.sistecreditotestapp.detallejuego.dominio.casosdeuso.ObtenerDetalleJuegoUC
import com.cristian.sistecreditotestapp.detallejuego.presentacion.estados.EstadoDetalleJuego
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleJuegoViewModel @Inject constructor(
    private val obtenerDetalleJuegoUC: ObtenerDetalleJuegoUC,
) : ViewModel() {

    private val _estadoDetalleJuego =
        MutableStateFlow<EstadoDetalleJuego>(EstadoDetalleJuego.Cargando)
    val estadoDetalleJuego: StateFlow<EstadoDetalleJuego> get() = _estadoDetalleJuego

    private val _mostrarDialogo = MutableStateFlow(false)
    val mostrarDialogo: StateFlow<Boolean> get() = _mostrarDialogo

    fun obtenerDetalleJuego(id: Int) {
        viewModelScope.launch {
            try {
                val detalleJuegoDominio = obtenerDetalleJuegoUC.invoke(id)
                val detalleJuegoPresentacion = detalleJuegoDominio.aPresentacion()
                _estadoDetalleJuego.value = EstadoDetalleJuego.Exitoso(detalleJuegoPresentacion)
            } catch (error: Exception) {
                _estadoDetalleJuego.value = EstadoDetalleJuego.Error(error)
            }
        }
    }

    fun cerrarDialogoError() {
        _mostrarDialogo.value = false
    }
}
