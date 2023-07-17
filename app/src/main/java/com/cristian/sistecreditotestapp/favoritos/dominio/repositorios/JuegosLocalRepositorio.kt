package com.cristian.sistecreditotestapp.favoritos.dominio.repositorios

import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito
import kotlinx.coroutines.flow.Flow

interface JuegosLocalRepositorio {
    suspend fun agregarJuegoFavorito(juegoFavorito: JuegoFavorito)

    suspend fun removerJuegoFavorito(juegoFavorito: JuegoFavorito)

    fun obtenerJuegosFavoritos(): Flow<List<JuegoFavorito>>
}
