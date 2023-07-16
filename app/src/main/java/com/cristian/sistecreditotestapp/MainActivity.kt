package com.cristian.sistecreditotestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cristian.sistecreditotestapp.comun.Rutas
import com.cristian.sistecreditotestapp.detallejuego.presentacion.pantallas.DetalleJuegoPantalla
import com.cristian.sistecreditotestapp.detallejuego.presentacion.viewmodel.DetalleJuegoViewModel
import com.cristian.sistecreditotestapp.favoritos.presentacion.pantallas.FavoritosPantalla
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.JuegosPantalla
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.viewmodel.JuegosViewModel
import com.cristian.sistecreditotestapp.ui.theme.SisteCreditoTestAppTheme
import com.cristian.sistecreditotestapp.utils.Constants
import com.cristian.sistecreditotestapp.utils.Constants.Companion.ID_JUEGO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val juegosViewModel: JuegosViewModel by viewModels()
        val detalleJuegoViewModel: DetalleJuegoViewModel by viewModels()

        setContent {
            SisteCreditoTestAppTheme {
                val navControlador = rememberNavController()
                NavHost(
                    navController = navControlador,
                    startDestination = Constants.PANTALLA_INICIO,
                ) {
                    composable(route = Rutas.pantallaInicio.ruta) {
                        JuegosPantalla(
                            navControlador,
                            juegosViewModel,
                        )
                    }
                    composable(
                        route = Rutas.pantallaDetalleJuego.ruta,
                        arguments = listOf(navArgument(ID_JUEGO) { type = NavType.IntType }),
                    ) { entrada ->
                        DetalleJuegoPantalla(
                            navControlador,
                            entrada.arguments?.getInt(ID_JUEGO) ?: 0,
                            detalleJuegoViewModel,
                        )
                    }
                    composable(route = Rutas.pantallaFavoritos.ruta) {
                        FavoritosPantalla()
                    }
                }
            }
        }
    }
}
