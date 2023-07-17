package com.cristian.sistecreditotestapp.detallejuego.presentacion.estados

import com.cristian.sistecreditotestapp.detallejuego.presentacion.modelos.DetalleJuego

sealed class EstadoDetalleJuego {
    object Cargando : EstadoDetalleJuego()
    data class Exitoso(val detalleJuego: DetalleJuego) : EstadoDetalleJuego()
    data class Error(val error: Throwable) : EstadoDetalleJuego()
}
