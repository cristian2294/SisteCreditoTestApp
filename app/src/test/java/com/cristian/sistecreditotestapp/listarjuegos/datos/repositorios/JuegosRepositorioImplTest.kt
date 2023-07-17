package com.cristian.sistecreditotestapp.listarjuegos.datos.repositorios

import com.cristian.sistecreditotestapp.listarjuegos.datos.api.JuegoApi
import com.cristian.sistecreditotestapp.listarjuegos.datos.modelos.Juego
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class JuegosRepositorioImplTest() {

    private val juegoApi: JuegoApi = mockk(relaxed = true)

    private lateinit var juegosRepositorioImpl: JuegosRepositorioImpl

    @Before
    fun setUp() {
        juegosRepositorioImpl = JuegosRepositorioImpl(juegoApi = juegoApi)
    }

    @After
    fun tearDown() {
        confirmVerified(juegoApi)
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
        coEvery { juegoApi.obtenerJuegos() } returns Response.success(juegos)

        // When
        juegosRepositorioImpl.obtenerJuegos()

        // Then
        coVerify { juegoApi.obtenerJuegos() }
    }
}
