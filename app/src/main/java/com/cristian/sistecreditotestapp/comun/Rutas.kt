package com.cristian.sistecreditotestapp.comun

import com.cristian.sistecreditotestapp.utils.Constants.Companion.PANTALLA_DETALLE_JUEGO
import com.cristian.sistecreditotestapp.utils.Constants.Companion.PANTALLA_FAVORITOS
import com.cristian.sistecreditotestapp.utils.Constants.Companion.PANTALLA_INICIO

sealed class Rutas(val ruta: String) {
    object pantallaInicio : Rutas(PANTALLA_INICIO)
    object pantallaDetalleJuego : Rutas(PANTALLA_DETALLE_JUEGO) {
        fun crearRuta(id: Int) = "detalle_juego/$id"
    }

    object pantallaFavoritos : Rutas(PANTALLA_FAVORITOS)
}
