package com.cristian.sistecreditotestapp.listarjuegos.datos.repositorios

import com.cristian.sistecreditotestapp.listarjuegos.datos.api.JuegoApi
import com.cristian.sistecreditotestapp.listarjuegos.datos.modelos.aDominio
import com.cristian.sistecreditotestapp.listarjuegos.dominio.modelos.Juego
import com.cristian.sistecreditotestapp.listarjuegos.dominio.repositorios.JuegosRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JuegosRepositorioImpl @Inject constructor(
    private val juegoApi: JuegoApi,
) : JuegosRepositorio {
    override suspend fun obtenerJuegos(): List<Juego> {
        return withContext(Dispatchers.IO) {
            val juegos = juegoApi.obtenerJuegos()
            juegos.body()?.map { it.aDominio() } ?: emptyList()
        }
    }
}
