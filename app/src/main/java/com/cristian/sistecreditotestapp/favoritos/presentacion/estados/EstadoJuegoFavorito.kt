package com.cristian.sistecreditotestapp.favoritos.presentacion.estados

import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito

sealed class EstadoJuegoFavorito {
    object Cargando : EstadoJuegoFavorito()
    data class Exitoso(val juegos: List<JuegoFavorito>) : EstadoJuegoFavorito()
    data class Error(val error: Throwable) : EstadoJuegoFavorito()
}
