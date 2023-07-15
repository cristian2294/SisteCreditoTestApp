package com.cristian.sistecreditotestapp.comun

sealed class Rutas(val ruta: String) {
    object pantallaInicio : Rutas("pantalla_inicio")
    object pantallaDetalleJuego : Rutas("detalle_juego")
    object pantallaFavoritos : Rutas("pantalla_favoritos")
}
