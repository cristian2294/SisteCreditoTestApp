package com.cristian.sistecreditotestapp.listarjuegos.datos.modelos

import com.google.gson.annotations.SerializedName
import com.cristian.sistecreditotestapp.listarjuegos.dominio.modelos.Juego as JuegoCapaDominio

data class Juego(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("thumbnail")
    val imagen: String,
    @SerializedName("short_description")
    val descripcionCorta: String,
    @SerializedName("game_url")
    val urlJuego: String,
    @SerializedName("genre")
    val genero: String,
    @SerializedName("platform")
    val plataforma: String,
    @SerializedName("publisher")
    val editora: String,
    @SerializedName("developer")
    val desarrollador: String,
    @SerializedName("release_date")
    val fechaLanzamiento: String,
)

fun Juego.aDominio(): JuegoCapaDominio {
    return JuegoCapaDominio(
        id = id,
        titulo = titulo,
        imagen = imagen,
        descripcionCorta = descripcionCorta,
        urlJuego = urlJuego,
        genero = genero,
        plataforma = plataforma,
        editora = editora,
        desarrollador = desarrollador,
        fechaLanzamiento = fechaLanzamiento,
    )
}
