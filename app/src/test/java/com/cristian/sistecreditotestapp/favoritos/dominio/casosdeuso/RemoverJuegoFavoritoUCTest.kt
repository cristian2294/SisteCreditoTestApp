package com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso

import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.dominio.repositorios.JuegosLocalRepositorio
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class RemoverJuegoFavoritoUCTest() {
    private val juegosLocalRepositorio: JuegosLocalRepositorio = mockk(relaxed = true)

    private lateinit var removerJuegoFavoritoUC: RemoverJuegoFavoritoUC

    @Before
    fun setUp() {
        removerJuegoFavoritoUC = RemoverJuegoFavoritoUC(juegosLocalRepositorio)
    }

    @After
    fun tearDown() {
        confirmVerified(juegosLocalRepositorio)
    }

    @Test
    fun agregarJuegoFavorito() = runBlocking {
        // Given
        val juegoFavorito = JuegoFavorito(
            id = 1,
            titulo = "Nombre del juego",
            imagen = "ruta/imagen.jpg",
            desarrollador = "Desarrollador del juego",
        )

        coEvery { juegosLocalRepositorio.removerJuegoFavorito(juegoFavorito) } returns Unit

        // When
        removerJuegoFavoritoUC.invoke(juegoFavorito)

        // Then
        coVerify { juegosLocalRepositorio.removerJuegoFavorito(juegoFavorito) }
    }
}
