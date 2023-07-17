package com.cristian.sistecreditotestapp.detallejuego.presentacion.modelos

import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito

data class DetalleJuego(
    val id: Int,
    val titulo: String,
    val imagen: String,
    val urlJuego: String,
    val genero: String,
    val desarrollador: String,
    val fechaLanzamiento: String,
    val descripcion: String,
    val requerimientosMinimos: RequerimientosMinimos?,
    val capturasPantalla: List<CapturaPantalla>,
) {
    fun aJuegoFavorito(detalleJuego: DetalleJuego): JuegoFavorito {
        return JuegoFavorito(
            id = detalleJuego.id,
            titulo = detalleJuego.titulo,
            imagen = detalleJuego.imagen,
            desarrollador = detalleJuego.desarrollador,
        )
    }
}

data class RequerimientosMinimos(
    val graficos: String,
    val memoria: String,
    val os: String,
    val procesador: String,
    val almacenamiento: String,
)

data class CapturaPantalla(
    val id: Int,
    val imagenCapturaPantalla: String,
)
