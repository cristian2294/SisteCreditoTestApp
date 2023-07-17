package com.cristian.sistecreditotestapp.favoritos.datos.repositorios

import com.cristian.sistecreditotestapp.favoritos.datos.db.dao.JuegoFavoritoDAO
import com.cristian.sistecreditotestapp.favoritos.datos.db.entidades.JuegoFavoritoEntidad
import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.dominio.repositorios.JuegosLocalRepositorio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JuegosLocalRepositorioImpl @Inject constructor(
    private val juegoFavoritoDAO: JuegoFavoritoDAO,
) : JuegosLocalRepositorio {

    val juegos: Flow<List<JuegoFavorito>> =
        juegoFavoritoDAO.obtenerJuegosFavoritos().map { juegosEntidades ->
            juegosEntidades.map { juegoEntidad ->
                juegoEntidad.aDominio()
            }
        }

    override suspend fun agregarJuegoFavorito(juegoFavorito: JuegoFavorito) {
        juegoFavoritoDAO.agregarJuegoFavorito(juegoFavorito.aEntidad())
    }

    override suspend fun removerJuegoFavorito(juegoFavorito: JuegoFavorito) {
        juegoFavoritoDAO.removerJuegoFavorito(juegoFavorito.aEntidad())
    }

    override fun obtenerJuegosFavoritos(): Flow<List<JuegoFavorito>> = juegos
}

fun JuegoFavorito.aEntidad(): JuegoFavoritoEntidad {
    return JuegoFavoritoEntidad(
        id = this.id,
        titulo = this.titulo,
        imagen = this.imagen,
        desarrollador = this.desarrollador,
    )
}
