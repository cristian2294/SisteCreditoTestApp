package com.cristian.sistecreditotestapp.listarjuegos.presentacion.modelos

data class Juego(
    val id: Int,
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
