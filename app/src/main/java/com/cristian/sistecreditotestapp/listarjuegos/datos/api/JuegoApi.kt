package com.cristian.sistecreditotestapp.listarjuegos.datos.api

import com.cristian.sistecreditotestapp.listarjuegos.datos.modelos.Juego
import com.cristian.sistecreditotestapp.utils.Constants.Companion.JUEGOS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface JuegoApi {
    @GET(JUEGOS_ENDPOINT)
    suspend fun obtenerJuegos(): Response<List<Juego>>
}
