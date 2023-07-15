package com.cristian.sistecreditotestapp.listarjuegos.datos.api

import com.cristian.sistecreditotestapp.listarjuegos.datos.modelos.Juego
import retrofit2.Response
import retrofit2.http.GET

interface JuegoApi {
    @GET("games")
    suspend fun obtenerJuegos(): Response<List<Juego>>
}
