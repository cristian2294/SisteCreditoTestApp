package com.cristian.sistecreditotestapp.detallejuego.datos.repositorios

import com.cristian.sistecreditotestapp.detallejuego.datos.api.DetalleJuegoApi
import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.DetalleJuego
import com.cristian.sistecreditotestapp.detallejuego.dominio.repositorios.DetalleJuegoRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetalleJuegoRepositorioImpl @Inject constructor(
    private val detalleJuegoApi: DetalleJuegoApi,
) : DetalleJuegoRepositorio {
    override suspend fun obtenerDetalleJuego(id: Int): DetalleJuego {
        return withContext(Dispatchers.IO) {
            val detalleJuego = detalleJuegoApi.obtenerDetalleJuego(id)
            detalleJuego.body()!!.aDominio()
        }
    }
}
