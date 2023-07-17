package com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso

import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.dominio.repositorios.JuegosLocalRepositorio
import javax.inject.Inject

class AgregarJuegoFavoritoUC @Inject constructor(
    private val juegosLocalRepositorio: JuegosLocalRepositorio,
) {
    suspend operator fun invoke(juegoFavorito: JuegoFavorito) {
        juegosLocalRepositorio.agregarJuegoFavorito(juegoFavorito)
    }
}
