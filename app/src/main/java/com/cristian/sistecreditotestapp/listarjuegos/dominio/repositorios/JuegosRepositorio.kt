package com.cristian.sistecreditotestapp.listarjuegos.dominio.repositorios

import com.cristian.sistecreditotestapp.listarjuegos.dominio.modelos.Juego

interface JuegosRepositorio {
    suspend fun obtenerJuegos(): List<Juego>
}
