package com.cristian.sistecreditotestapp.detallejuego.presentacion.pantallas

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.cristian.sistecreditotestapp.R
import com.cristian.sistecreditotestapp.detallejuego.presentacion.estados.EstadoDetalleJuego
import com.cristian.sistecreditotestapp.detallejuego.presentacion.modelos.CapturaPantalla
import com.cristian.sistecreditotestapp.detallejuego.presentacion.modelos.DetalleJuego
import com.cristian.sistecreditotestapp.detallejuego.presentacion.viewmodel.DetalleJuegoViewModel
import com.cristian.sistecreditotestapp.favoritos.presentacion.viewmodel.JuegoFavoritoViewModel
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.MenuOpciones
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.MostrarComponenteCarga
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.MostrarDialogoError

@Composable
fun DetalleJuegoPantalla(
    navControlador: NavHostController,
    id: Int,
    detalleJuegoViewModel: DetalleJuegoViewModel,
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
) {
    detalleJuegoViewModel.obtenerDetalleJuego(id)
    val mostrarDialogoError: Boolean by detalleJuegoViewModel.mostrarDialogo.collectAsState()
    val estadoDestalleJuego by detalleJuegoViewModel.estadoDetalleJuego.collectAsState()

    when (estadoDestalleJuego) {
        EstadoDetalleJuego.Cargando -> {
            MostrarComponenteCarga()
        }

        is EstadoDetalleJuego.Error -> {
            MostrarDialogoError(
                mostrarDialogoError = mostrarDialogoError,
                entendido = { detalleJuegoViewModel.cerrarDialogoError() },
                error = (estadoDestalleJuego as EstadoDetalleJuego.Error).error,
            )
        }

        is EstadoDetalleJuego.Exitoso -> {
            CargarDetalleJuego(
                navControlador,
                (estadoDestalleJuego as EstadoDetalleJuego.Exitoso).detalleJuego,
                juegoFavoritoViewModel,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargarDetalleJuego(
    navControlador: NavHostController,
    detalleJuego: DetalleJuego,
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
) {
    Scaffold(
        bottomBar = {
            MenuOpciones(navControlador, modifier = Modifier)
        },
        floatingActionButton = {
            BotonAgregarAFavoritos(
                modifier = Modifier,
                juegoFavoritoViewModel,
                detalleJuego,
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { contentPadding ->
        LazyColumn(modifier = Modifier.padding(contentPadding).fillMaxWidth()) {
            item {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (
                        botonAtras,
                        contenedorDetalleJuego,
                    ) = createRefs()

                    ContenedorDetalleJuego(
                        detalleJuego,
                        Modifier.constrainAs(contenedorDetalleJuego) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    )

                    BotonAtras(
                        navControlador,
                        Modifier
                            .padding(top = dimensionResource(id = R.dimen.dimen_16dp))
                            .constrainAs(botonAtras) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            },
                    )
                }
            }
        }
    }
}

@Composable
fun BotonAtras(
    navControlador: NavHostController,
    modifier: Modifier,
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
            .clickable { navControlador.popBackStack() },
    )
}

@Composable
fun ContenedorDetalleJuego(
    detalleJuego: DetalleJuego,
    modifier: Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ComponenteImagenDetalleJuego(detalleJuego)
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.dimen_16dp)))
        ComponenteTitulo(modifier, detalleJuego)
        ComponenteDatosDetalleJuego(modifier, detalleJuego)
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.dimen_16dp)))
        ComponenteCapturasPantalla(modifier, detalleJuego.capturasPantalla)
    }
}

@Composable
fun ComponenteImagenDetalleJuego(detalleJuego: DetalleJuego) {
    AsyncImage(
        model = detalleJuego.imagen,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.dimen_240dp))
            .clip(
                RoundedCornerShape(
                    bottomStart = dimensionResource(id = R.dimen.dimen_16dp),
                    bottomEnd = dimensionResource(id = R.dimen.dimen_16dp),
                ),
            ),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun ComponenteTitulo(modifier: Modifier, detalleJuego: DetalleJuego) {
    val openSans = FontFamily(Font(R.font.open_sans))
    Text(
        modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8dp)),
        text = detalleJuego.titulo,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = openSans,
    )
}

@Composable
fun ComponenteDatosDetalleJuego(modifier: Modifier, detalleJuego: DetalleJuego) {
    val openSans = FontFamily(Font(R.font.open_sans))
    Column(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_12dp))) {
        ComponenteDesarrolladoraDetalle(modifier, detalleJuego, openSans)
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.dimen_8dp)))
        ComponenteUrlDetalle(modifier, detalleJuego, openSans)
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.dimen_16dp)))
        Divider(modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8dp)))
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.dimen_16dp)))
        ComponenteDescripcion(modifier, detalleJuego, openSans)
    }
}

@Composable
fun ComponenteDesarrolladoraDetalle(
    modifier: Modifier,
    detalleJuego: DetalleJuego,
    openSans: FontFamily,
) {
    Row(modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8dp))) {
        Text(
            text = stringResource(R.string.pantalla_detalle_juego_desarrolladora_titulo),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = openSans,
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8dp)),
            text = detalleJuego.desarrollador,
            fontSize = 14.sp,
            fontFamily = openSans,
        )
    }
}

@Composable
fun ComponenteUrlDetalle(modifier: Modifier, detalleJuego: DetalleJuego, openSans: FontFamily) {
    Row(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_8dp),
            ),
    ) {
        Text(
            text = stringResource(R.string.pantalla_detalle_juego_url_titulo),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = openSans,
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8dp)),
            text = detalleJuego.urlJuego,
            maxLines = 1,
            fontSize = 12.sp,
            fontFamily = openSans,
        )
    }
}

@Composable
fun ComponenteDescripcion(modifier: Modifier, detalleJuego: DetalleJuego, openSans: FontFamily) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_12dp)),
        text = detalleJuego.descripcion,
        fontSize = 12.sp,
        fontFamily = openSans,
    )
}

@Composable
fun ComponenteCapturasPantalla(modifier: Modifier, capturasPantalla: List<CapturaPantalla>) {
    if (capturasPantalla.isNotEmpty()) {
        val openSans = FontFamily(Font(R.font.open_sans))
        Text(
            text = stringResource(R.string.pantalla_detalle_juego_capturas_pantalla_titulo),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = openSans,
            modifier = modifier.padding(start = dimensionResource(id = R.dimen.dimen_16dp)),
        )
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.dimen_8dp)))
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.dimen_8dp),
                    end = dimensionResource(id = R.dimen.dimen_8dp),
                    top = dimensionResource(id = R.dimen.dimen_8dp),
                    bottom = dimensionResource(id = R.dimen.dimen_16dp),
                ),
        ) {
            items(capturasPantalla, key = { it.id }) { capturaPantalla ->
                ItemCapturaPantalla(capturaPantalla)
            }
        }
    }
}

@Composable
fun ItemCapturaPantalla(capturaPantalla: CapturaPantalla) {
    AsyncImage(
        model = capturaPantalla.imagenCapturaPantalla,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.dimen_200dp))
            .clip(
                RoundedCornerShape(size = dimensionResource(id = R.dimen.dimen_4dp)),
            ),
    )
}

@Composable
fun BotonAgregarAFavoritos(
    modifier: Modifier,
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
    detalleJuego: DetalleJuego,
) {
    val contexto = LocalContext.current
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            juegoFavoritoViewModel.agregarJuegoFavorito(
                detalleJuego.aJuegoFavorito(detalleJuego),
            )
            Toast.makeText(
                contexto,
                R.string.pantalla_detalle_juego_boton_agregar_favoritos,
                Toast.LENGTH_SHORT,
            ).show()
        },
        containerColor = Color.White,
        contentColor = Color.Red,
    ) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
    }
}
