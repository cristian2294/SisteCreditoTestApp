package com.cristian.sistecreditotestapp.listarjuegos.dominio.casosdeuso

import com.cristian.sistecreditotestapp.listarjuegos.dominio.modelos.Juego
import com.cristian.sistecreditotestapp.listarjuegos.dominio.repositorios.JuegosRepositorio
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class ObtenerJuegosUCTest() {

    private val juegosRepositorio: JuegosRepositorio = mockk(relaxed = true)

    private lateinit var obtenerJuegosUC: ObtenerJuegosUC

    @Before
    fun setUp() {
        obtenerJuegosUC = ObtenerJuegosUC(juegosRepositorio = juegosRepositorio)
    }

    @After
    fun tearDown() {
        confirmVerified(juegosRepositorio)
    }

    @Test
    fun `obtenerJuegos debe devolver una lista de juegos`() = runBlocking {
        // Given
        val juego1 = Juego(
            id = 1,
            titulo = "Mi Juego",
            imagen = "https://example.com/imagen.jpg",
            descripcionCorta = "Descripción corta del juego",
            urlJuego = "https://example.com/juego",
            genero = "Acción",
            plataforma = "PC",
            editora = "Editora Ejemplo",
            desarrollador = "Desarrollador Ejemplo",
            fechaLanzamiento = "2023-07-01",
        )
        val juego2 = Juego(
            id = 2,
            titulo = "Mi Juego2",
            imagen = "https://example.com/imagen.jpg",
            descripcionCorta = "Descripción corta del juego",
            urlJuego = "https://example.com/juego",
            genero = "Acción",
            plataforma = "PC",
            editora = "Editora Ejemplo",
            desarrollador = "Desarrollador Ejemplo",
            fechaLanzamiento = "2023-07-01",
        )
        val juegos = listOf(juego1, juego2)
        coEvery { juegosRepositorio.obtenerJuegos() } returns juegos

        // When
        val resultado = obtenerJuegosUC.invoke()

        // Then
        assertEquals(juegos, resultado)
        coVerify { juegosRepositorio.obtenerJuegos() }
    }
}
