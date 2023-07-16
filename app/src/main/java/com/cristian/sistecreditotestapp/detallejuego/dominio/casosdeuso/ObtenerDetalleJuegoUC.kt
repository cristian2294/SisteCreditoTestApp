package com.cristian.sistecreditotestapp.detallejuego.dominio.casosdeuso

import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.DetalleJuego
import com.cristian.sistecreditotestapp.detallejuego.dominio.repositorios.DetalleJuegoRepositorio
import javax.inject.Inject

class ObtenerDetalleJuegoUC @Inject constructor(
    private val detalleJuegoRepositorio: DetalleJuegoRepositorio,
) {
    suspend operator fun invoke(id: Int): DetalleJuego =
        detalleJuegoRepositorio.obtenerDetalleJuego(id)
}
