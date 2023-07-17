package com.cristian.sistecreditotestapp.favoritos.datos.repositorios

import com.cristian.sistecreditotestapp.favoritos.datos.db.dao.JuegoFavoritoDAO
import com.cristian.sistecreditotestapp.favoritos.datos.db.entidades.JuegoFavoritoEntidad
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class JuegosLocalRepositorioImplTest() {

    private val juegoFavoritoDAO: JuegoFavoritoDAO = mockk(relaxed = true)

    private lateinit var juegosLocalRepositorioImpl: JuegosLocalRepositorioImpl

    @Before
    fun setUp() {
        juegosLocalRepositorioImpl = JuegosLocalRepositorioImpl(juegoFavoritoDAO)
    }

    @After
    fun tearDown() {
        confirmVerified(juegoFavoritoDAO)
    }

    @Test
    fun agregarJuegoFavorito() = runBlocking {
        // Given
        val juegoFavorito = JuegoFavoritoEntidad(
            id = 1,
            titulo = "Nombre del juego",
            imagen = "ruta/imagen.jpg",
            desarrollador = "Desarrollador del juego",
        )

        coEvery { juegoFavoritoDAO.agregarJuegoFavorito(juegoFavorito) } returns Unit

        // When
        juegosLocalRepositorioImpl.agregarJuegoFavorito(juegoFavorito.aDominio())

        // Then
        coVerifyOrder {
            juegoFavoritoDAO.obtenerJuegosFavoritos()
            juegoFavoritoDAO.agregarJuegoFavorito(juegoFavorito)
        }
    }

    @Test
    fun removerJuegoFavorito() = runBlocking {
        // Given
        val juegoFavorito = JuegoFavoritoEntidad(
            id = 1,
            titulo = "Nombre del juego",
            imagen = "ruta/imagen.jpg",
            desarrollador = "Desarrollador del juego",
        )

        coEvery { juegoFavoritoDAO.removerJuegoFavorito(juegoFavorito) } returns Unit

        // When
        juegosLocalRepositorioImpl.removerJuegoFavorito(juegoFavorito.aDominio())

        // Then
        coVerifyOrder {
            juegoFavoritoDAO.obtenerJuegosFavoritos()
            juegoFavoritoDAO.removerJuegoFavorito(juegoFavorito)
        }
    }

    @Test
    fun obtenerJuegosFavoritos() = runBlocking {
        // Given
        val juegoFavorito = JuegoFavoritoEntidad(
            id = 1,
            titulo = "Nombre del juego",
            imagen = "ruta/imagen.jpg",
            desarrollador = "Desarrollador del juego",
        )

        val juegosFavoritos = listOf(juegoFavorito)

        coEvery { juegoFavoritoDAO.obtenerJuegosFavoritos() } returns flow { juegosFavoritos }

        // When
        juegosLocalRepositorioImpl.obtenerJuegosFavoritos()

        // Then
        coVerify { juegoFavoritoDAO.obtenerJuegosFavoritos() }
    }
}
