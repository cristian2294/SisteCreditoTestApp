package com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso

import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.dominio.repositorios.JuegosLocalRepositorio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObtenerJuegosFavoritosUC @Inject constructor(
    private val juegosLocalRepositorio: JuegosLocalRepositorio,
) {
    operator fun invoke(): Flow<List<JuegoFavorito>> =
        juegosLocalRepositorio.obtenerJuegosFavoritos()
}
