package com.cristian.sistecreditotestapp.detallejuego.datos.api

import com.cristian.sistecreditotestapp.detallejuego.datos.modelos.DetalleJuego
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DetalleJuegoApi {
    @GET("game?")
    suspend fun obtenerDetalleJuego(@Query("id") id: Int): Response<DetalleJuego>
}
