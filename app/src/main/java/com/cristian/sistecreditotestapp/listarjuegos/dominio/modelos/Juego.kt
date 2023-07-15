package com.cristian.sistecreditotestapp.listarjuegos.dominio.modelos

import com.cristian.sistecreditotestapp.listarjuegos.presentacion.modelos.Juego as JuegoPresentacion

data class Juego(
    val id: String,
    val titulo: String,
    val imagen: String,
    val descripcionCorta: String,
    val urlJuego: String,
    val genero: String,
    val plataforma: String,
    val editora: String,
    val desarrollador: String,
    val fechaLanzamiento: String,
)

fun Juego.aPresentacion(): JuegoPresentacion {
    return JuegoPresentacion(
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
