package com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.cristian.sistecreditotestapp.R
import com.cristian.sistecreditotestapp.comun.Rutas
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.estados.EstadoJuego
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.modelos.Juego
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.viewmodel.JuegosViewModel

@Composable
fun JuegosPantalla(navControlador: NavHostController, juegosViewModel: JuegosViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (menuOpciones, listaJuegos) = createRefs()
        ListaJuegos(
            navControlador,
            juegosViewModel,
            Modifier.constrainAs(listaJuegos) {
                top.linkTo(parent.top, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )

        MenuOpciones(
            navControlador,
            Modifier.constrainAs(menuOpciones) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
    }
}

@Composable
fun CargarListadoJuegos(
    modifier: Modifier,
    navControlador: NavHostController,
    juegos: List<Juego>,
) {
    LazyColumn(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.dimen_16dp),
            end = dimensionResource(id = R.dimen.dimen_16dp),
            top = dimensionResource(id = R.dimen.dimen_16dp),
        ),
    ) {
        items(juegos, key = { it.id }) { juego ->
            ItemJuego(juego = juego, navControlador = navControlador)
        }
    }
}

@Composable
fun ListaJuegos(
    navControlador: NavHostController,
    juegosViewModel: JuegosViewModel,
    modifier: Modifier,
) {
    val mostrarDialogoError: Boolean by juegosViewModel.mostrarDialogo.collectAsState()
    val estadoJuegos by juegosViewModel.estadoJuegos.collectAsState()
    when (estadoJuegos) {
        EstadoJuego.Cargando -> {
            MostrarComponenteCarga()
        }

        is EstadoJuego.Error -> {
            MostrarDialogoError(
                mostrarDialogoError = mostrarDialogoError,
                entendido = { juegosViewModel.cerrarDialogoError() },
                error = (estadoJuegos as EstadoJuego.Error).error,
            )
        }

        is EstadoJuego.Exitoso -> {
            CargarListadoJuegos(
                modifier,
                navControlador,
                (estadoJuegos as EstadoJuego.Exitoso).juegos,
            )
        }
    }
}

@Composable
fun MostrarComponenteCarga() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun MostrarDialogoError(
    mostrarDialogoError: Boolean,
    entendido: () -> Unit,
    error: Throwable,
) {
    if (mostrarDialogoError) {
        AlertDialog(
            onDismissRequest = { entendido() },
            title = {
                Text(
                    text = stringResource(
                        id = R.string.pantalla_inicio_error_dialogo_titulo,
                    ),
                )
            },
            text = {
                Text(
                    text = stringResource(
                        id = R.string.pantalla_inicio_error_dialogo_descripcion,
                        error.toString(),
                    ),
                )
            },
            confirmButton = {
                TextButton(onClick = { entendido() }) {
                    Text(
                        text = stringResource(
                            id = R.string.pantalla_inicio_error_dialogo_boton_entendido,
                        ),
                    )
                }
            },
        )
    }
}

@Composable
fun ItemJuego(juego: Juego, navControlador: NavHostController) {
    val openSans = FontFamily(Font(R.font.open_sans))
    val iconoPlataforma = obtenerIconoPorPlataforma(juego.plataforma)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.dimen_16dp))
            .clickable { navControlador.navigate(Rutas.pantallaDetalleJuego.crearRuta(juego.id)) },
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.dimen_4dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = juego.imagen,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = juego.titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = openSans,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.dimen_8dp),
                    top = dimensionResource(id = R.dimen.dimen_12dp),
                ),
                color = Color.Black,
            )

            Text(
                text = juego.descripcionCorta,
                fontSize = 12.sp,
                fontFamily = openSans,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.dimen_8dp),
                    end = dimensionResource(id = R.dimen.dimen_8dp),
                    top = dimensionResource(id = R.dimen.dimen_8dp),
                ),
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.dimen_8dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Icon(
                    imageVector = iconoPlataforma,
                    contentDescription = stringResource(
                        id = R.string.pantalla_inicio_descipcion_contenido_desarrolladora,
                    ),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.dimen_40dp))
                        .padding(
                            end = dimensionResource(id = R.dimen.dimen_4dp),
                            bottom = dimensionResource(id = R.dimen.dimen_8dp),
                        ),
                )
                Text(
                    text = juego.genero,
                    fontSize = 14.sp,
                    fontFamily = openSans,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.dimen_4dp),
                            end = dimensionResource(id = R.dimen.dimen_8dp),
                            bottom = dimensionResource(id = R.dimen.dimen_8dp),
                        ),
                )
            }
        }
    }
}

fun obtenerIconoPorPlataforma(plataforma: String): ImageVector {
    return if (plataforma == PC_WINDOWS) {
        Icons.Default.Computer
    } else {
        Icons.Default.DesktopWindows
    }
}

@Composable
fun MenuOpciones(navControlador: NavHostController, modifier: Modifier) {
    var indice by rememberSaveable { mutableStateOf(0) }
    val contexto = LocalContext.current
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            selected = indice == 0,
            onClick = {
                indice = 0
                navControlador.navigate(Rutas.pantallaInicio.ruta)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(
                        id = R.string.pantalla_inicio_opcion_menu_inicio,
                    ),
                )
            },
            label = {
                Text(text = stringResource(id = R.string.pantalla_inicio_opcion_menu_inicio))
            },
        )
        NavigationBarItem(
            selected = indice == 1,
            onClick = {
                indice = 1
                navControlador.navigate(Rutas.pantallaFavoritos.ruta)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(
                        id = R.string.pantalla_inicio_opcion_menu_favoritos,
                    ),
                )
            },
            label = {
                Text(text = stringResource(id = R.string.pantalla_inicio_opcion_menu_favoritos))
            },
        )
        NavigationBarItem(
            selected = indice == 2,
            onClick = {
                indice = 2
                Toast.makeText(
                    contexto,
                    R.string.pantalla_categorias_proximamente,
                    Toast.LENGTH_SHORT,
                ).show()
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Category,
                    contentDescription = stringResource(
                        id = R.string.pantalla_inicio_opcion_menu_categorias,
                    ),
                )
            },
            label = {
                Text(text = stringResource(id = R.string.pantalla_inicio_opcion_menu_categorias))
            },
        )
    }
}

private const val PC_WINDOWS = "PC (Windows)"
