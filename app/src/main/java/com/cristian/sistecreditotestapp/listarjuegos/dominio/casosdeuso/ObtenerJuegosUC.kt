package com.cristian.sistecreditotestapp.listarjuegos.dominio.casosdeuso

import com.cristian.sistecreditotestapp.listarjuegos.dominio.repositorios.JuegosRepositorio
import javax.inject.Inject

class ObtenerJuegosUC @Inject constructor(
    private val juegosRepositorio: JuegosRepositorio,
) {
    suspend operator fun invoke() = juegosRepositorio.obtenerJuegos()
}
