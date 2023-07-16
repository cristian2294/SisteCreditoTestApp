package com.cristian.sistecreditotestapp.detallejuego.datos.modelos

import com.google.gson.annotations.SerializedName
import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.CapturaPantalla as CapturaPantallaCapaDominio
import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.DetalleJuego as DetalleJuegoCapaDominio
import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.RequerimientosMinimos as RequerimientoMinimosCapaDominio

data class DetalleJuego(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("thumbnail")
    val imagen: String,
    @SerializedName("game_url")
    val urlJuego: String,
    @SerializedName("genre")
    val genero: String,
    @SerializedName("developer")
    val desarrollador: String,
    @SerializedName("release_date")
    val fechaLanzamiento: String,
    @SerializedName("description")
    val descripcion: String,
    @SerializedName("minimum_system_requirements")
    val requerimientosMinimos: RequerimientosMinimos? = null,
    @SerializedName("screenshots")
    val capturasPantalla: List<CapturaPantalla> = arrayListOf(),
) {
    fun aDominio(): DetalleJuegoCapaDominio {
        return DetalleJuegoCapaDominio(
            id = id,
            titulo = titulo,
            imagen = imagen,
            urlJuego = urlJuego,
            genero = genero,
            desarrollador = desarrollador,
            fechaLanzamiento = fechaLanzamiento,
            descripcion = descripcion,
            requerimientosMinimos = requerimientosMinimos?.aDominio(),
            capturasPantalla = capturasPantalla.map { it.aDominio() },
        )
    }
}

data class RequerimientosMinimos(
    @SerializedName("graphics")
    val graficos: String,
    @SerializedName("memory")
    val memoria: String,
    @SerializedName("os")
    val os: String,
    @SerializedName("processor")
    val procesador: String,
    @SerializedName("storage")
    val almacenamiento: String,
) {
    fun aDominio(): RequerimientoMinimosCapaDominio {
        return RequerimientoMinimosCapaDominio(
            graficos = graficos,
            memoria = memoria,
            os = os,
            procesador = procesador,
            almacenamiento = almacenamiento,
        )
    }
}

data class CapturaPantalla(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val imagenCapturaPantalla: String,
) {
    fun aDominio(): CapturaPantallaCapaDominio {
        return CapturaPantallaCapaDominio(id = id, imagenCapturaPantalla = imagenCapturaPantalla)
    }
}
