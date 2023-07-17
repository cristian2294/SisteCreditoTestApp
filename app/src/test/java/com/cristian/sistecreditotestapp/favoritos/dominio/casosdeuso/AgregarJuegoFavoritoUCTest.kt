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

class AgregarJuegoFavoritoUCTest() {

    private val juegosLocalRepositorio: JuegosLocalRepositorio = mockk(relaxed = true)

    private lateinit var agregarJuegosFavoritoUC: AgregarJuegoFavoritoUC

    @Before
    fun setUp() {
        agregarJuegosFavoritoUC = AgregarJuegoFavoritoUC(juegosLocalRepositorio)
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

        coEvery { juegosLocalRepositorio.agregarJuegoFavorito(juegoFavorito) } returns Unit

        // When
        agregarJuegosFavoritoUC.invoke(juegoFavorito)

        // Then
        coVerify { juegosLocalRepositorio.agregarJuegoFavorito(juegoFavorito) }
    }
}
