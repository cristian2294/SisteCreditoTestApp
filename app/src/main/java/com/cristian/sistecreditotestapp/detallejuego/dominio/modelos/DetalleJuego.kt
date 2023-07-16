package com.cristian.sistecreditotestapp.detallejuego.dominio.modelos

import com.cristian.sistecreditotestapp.detallejuego.presentacion.modelos.DetalleJuego
import com.cristian.sistecreditotestapp.detallejuego.presentacion.modelos.CapturaPantalla as CapturaPantallaCapaPresentacion
import com.cristian.sistecreditotestapp.detallejuego.presentacion.modelos.RequerimientosMinimos as RequerimientosMinimosCapaPresentacion

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
    fun aPresentacion(): DetalleJuego {
        return DetalleJuego(
            id = id,
            titulo = titulo,
            imagen = imagen,
            urlJuego = urlJuego,
            genero = genero,
            desarrollador = desarrollador,
            fechaLanzamiento = fechaLanzamiento,
            descripcion = descripcion,
            requerimientosMinimos = requerimientosMinimos?.aPresentacion(),
            capturasPantalla = capturasPantalla.map { it.aPresentacion() },
        )
    }
}

data class RequerimientosMinimos(
    val graficos: String,
    val memoria: String,
    val os: String,
    val procesador: String,
    val almacenamiento: String,
) {
    fun aPresentacion(): RequerimientosMinimosCapaPresentacion {
        return RequerimientosMinimosCapaPresentacion(
            graficos = graficos,
            memoria = memoria,
            os = os,
            procesador = procesador,
            almacenamiento = almacenamiento,
        )
    }
}

data class CapturaPantalla(
    val id: Int,
    val imagenCapturaPantalla: String,
) {
    fun aPresentacion(): CapturaPantallaCapaPresentacion {
        return CapturaPantallaCapaPresentacion(
            id = id,
            imagenCapturaPantalla = imagenCapturaPantalla,
        )
    }
}
