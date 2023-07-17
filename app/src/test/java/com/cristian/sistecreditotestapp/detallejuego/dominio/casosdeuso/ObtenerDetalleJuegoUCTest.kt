package com.cristian.sistecreditotestapp.detallejuego.dominio.casosdeuso

import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.CapturaPantalla
import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.DetalleJuego
import com.cristian.sistecreditotestapp.detallejuego.dominio.modelos.RequerimientosMinimos
import com.cristian.sistecreditotestapp.detallejuego.dominio.repositorios.DetalleJuegoRepositorio
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class ObtenerDetalleJuegoUCTest() {

    private val detalleJuegoRepositorio: DetalleJuegoRepositorio = mockk(relaxed = true)

    private lateinit var obtenerDetalleJuegoUC: ObtenerDetalleJuegoUC

    @Before
    fun setUp() {
        obtenerDetalleJuegoUC = ObtenerDetalleJuegoUC(detalleJuegoRepositorio)
    }

    @After
    fun tearDown() {
        confirmVerified(detalleJuegoRepositorio)
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

        coEvery { detalleJuegoRepositorio.obtenerDetalleJuego(id) } returns detalleJuego

        // When
        val resultado = obtenerDetalleJuegoUC.invoke(id)

        // Then
        assertEquals(resultado, detalleJuego)
        coVerify { detalleJuegoRepositorio.obtenerDetalleJuego(id) }
    }
}
