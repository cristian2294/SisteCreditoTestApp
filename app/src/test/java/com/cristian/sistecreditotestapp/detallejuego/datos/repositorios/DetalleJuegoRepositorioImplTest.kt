package com.cristian.sistecreditotestapp.detallejuego.datos.repositorios

import com.cristian.sistecreditotestapp.detallejuego.datos.api.DetalleJuegoApi
import com.cristian.sistecreditotestapp.detallejuego.datos.modelos.CapturaPantalla
import com.cristian.sistecreditotestapp.detallejuego.datos.modelos.DetalleJuego
import com.cristian.sistecreditotestapp.detallejuego.datos.modelos.RequerimientosMinimos
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DetalleJuegoRepositorioImplTest() {

    private val detalleJuegoApi: DetalleJuegoApi = mockk(relaxed = true)

    private lateinit var detalleJuegoRepositorioImpl: DetalleJuegoRepositorioImpl

    @Before
    fun setUp() {
        detalleJuegoRepositorioImpl = DetalleJuegoRepositorioImpl(detalleJuegoApi)
    }

    @After
    fun tearDown() {
        confirmVerified(detalleJuegoApi)
    }

    @Test
    fun `obtenerDetalleJuego debe devolver un un DetalleJuego`() = runBlocking {
        // Given
        val id = 1
        val detalleJuego = DetalleJuego(
            id = 1,
            titulo = "Nombre del juego",
            imagen = "ruta/imagen.jpg",
            urlJuego = "https://ejemplo.com/juego",
            genero = "Acción",
            desarrollador = "Desarrollador del juego",
            fechaLanzamiento = "2022-01-01",
            descripcion = "Descripción del juego",
            requerimientosMinimos = RequerimientosMinimos(
                graficos = "NVIDIA GeForce GTX 1060",
                memoria = "8 GB",
                os = "Windows 10",
                procesador = "Intel Core i5",
                almacenamiento = "50 GB",
            ),
            capturasPantalla = listOf(
                CapturaPantalla(
                    id = 1,
                    imagenCapturaPantalla = "ruta/captura1.jpg",
                ),
                CapturaPantalla(
                    id = 2,
                    imagenCapturaPantalla = "ruta/captura2.jpg",
                ),
            ),
        )

        coEvery { detalleJuegoApi.obtenerDetalleJuego(id) } returns Response.success(detalleJuego)

        // When
        detalleJuegoRepositorioImpl.obtenerDetalleJuego(id)

        // Then
        coVerify { detalleJuegoApi.obtenerDetalleJuego(id) }
    }
}
