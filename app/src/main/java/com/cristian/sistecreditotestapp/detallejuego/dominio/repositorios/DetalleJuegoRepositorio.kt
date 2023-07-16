package com.cristian.sistecreditotestapp.detallejuego.dominio.repositorios

import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.DetalleJuego

interface DetalleJuegoRepositorio {
    suspend fun obtenerDetalleJuego(id: Int): DetalleJuego
}
