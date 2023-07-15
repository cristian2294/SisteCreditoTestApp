package com.cristian.sistecreditotestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cristian.sistecreditotestapp.comun.Rutas
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.JuegosPantalla
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.viewmodel.JuegosViewModel
import com.cristian.sistecreditotestapp.ui.theme.SisteCreditoTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val juegosViewModel: JuegosViewModel by viewModels()

        setContent {
            SisteCreditoTestAppTheme {
                val navControlador = rememberNavController()
                NavHost(navController = navControlador, startDestination = "pantalla_inicio") {
                    composable(route = Rutas.pantallaInicio.ruta) {
                        JuegosPantalla(
                            navControlador,
                            juegosViewModel,
                        )
                    }
                }
            }
        }
    }
}
