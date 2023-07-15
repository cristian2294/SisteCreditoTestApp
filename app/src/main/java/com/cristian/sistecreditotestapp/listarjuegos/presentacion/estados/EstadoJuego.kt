package com.cristian.sistecreditotestapp.listarjuegos.presentacion.estados

import com.cristian.sistecreditotestapp.listarjuegos.presentacion.modelos.Juego

sealed class EstadoJuego {
    object Cargando : EstadoJuego()
    data class Exitoso(val juegos: List<Juego>) : EstadoJuego()
    data class Error(val error: Throwable) : EstadoJuego()
}
