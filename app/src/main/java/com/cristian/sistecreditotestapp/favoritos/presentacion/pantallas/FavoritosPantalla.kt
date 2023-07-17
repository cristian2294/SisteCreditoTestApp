package com.cristian.sistecreditotestapp.favoritos.presentacion.pantallas

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.cristian.sistecreditotestapp.R
import com.cristian.sistecreditotestapp.detallejuego.presentacion.pantallas.BotonAtras
import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.presentacion.estados.EstadoJuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.presentacion.viewmodel.JuegoFavoritoViewModel
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.MenuOpciones
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.MostrarComponenteCarga
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.pantallas.MostrarDialogoError

@Composable
fun FavoritosPantalla(
    navControlador: NavHostController,
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
) {
    val mostrarDialogoError: Boolean by juegoFavoritoViewModel.mostrarDialogo.collectAsState()

    val estadoJuegoFavorito by juegoFavoritoViewModel.estadoJuegoFavorito.collectAsState()
    when (estadoJuegoFavorito) {
        EstadoJuegoFavorito.Cargando -> {
            MostrarComponenteCarga()
        }

        is EstadoJuegoFavorito.Error -> {
            MostrarDialogoError(
                mostrarDialogoError = mostrarDialogoError,
                entendido = { juegoFavoritoViewModel.cerrarDialogoError() },
                error = (estadoJuegoFavorito as EstadoJuegoFavorito.Error).error,
            )
        }

        is EstadoJuegoFavorito.Exitoso -> {
            CargarListaJuegosFavoritos(
                juegoFavoritoViewModel,
                navControlador,
                (estadoJuegoFavorito as EstadoJuegoFavorito.Exitoso).juegos,
            )
        }
    }
}

@Composable
fun ContenedorJuegoFavorito(
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
    juegoFavorito: JuegoFavorito,
    modifier: Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.dimen_4dp),
                start = dimensionResource(id = R.dimen.dimen_16dp),
                end = dimensionResource(id = R.dimen.dimen_16dp),
                bottom = dimensionResource(id = R.dimen.dimen_16dp),
            ),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.dimen_4dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        ConstraintLayout(modifier = modifier.fillMaxWidth()) {
            val (
                componenteImagenFavorito,
                componenteDatosJuegoFavorito,
                componenteIconoRemoverFavorito,
            ) = createRefs()

            ComponenteImagenJuegoFavorito(
                juegoFavorito.imagen,
                modifier = Modifier.constrainAs(componenteImagenFavorito) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
            )

            ComponenteDatosJuegoFavorito(
                juegoFavorito,
                modifier = Modifier.constrainAs(componenteDatosJuegoFavorito) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(componenteImagenFavorito.end, margin = 4.dp)
                },
            )

            ComponenteIconoRemoverFavorito(
                juegoFavorito,
                juegoFavoritoViewModel,
                modifier = Modifier.constrainAs(componenteIconoRemoverFavorito) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargarListaJuegosFavoritos(
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
    navControlador: NavHostController,
    juegos: List<JuegoFavorito>,
) {
    Scaffold(
        bottomBar = {
            MenuOpciones(navControlador, modifier = Modifier)
        },
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth(),
        ) {
            item {
                BotonAtras(
                    navControlador,
                    Modifier.padding(top = dimensionResource(id = R.dimen.dimen_16dp)),
                )
            }
            items(juegos, key = { it.id }) { juegoFavorito ->
                ItemJuegoFavorito(
                    juegoFavoritoViewModel,
                    juegoFavorito,
                )
            }
        }
    }
}

@Composable
fun ItemJuegoFavorito(
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
    juegoFavorito: JuegoFavorito,
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val contenedorJuegoFavorito = createRef()
        ContenedorJuegoFavorito(
            juegoFavoritoViewModel,
            juegoFavorito,
            Modifier
                .constrainAs(contenedorJuegoFavorito) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        )
    }
}

@Composable
fun ComponenteImagenJuegoFavorito(imagenJuegoFavorito: String, modifier: Modifier) {
    AsyncImage(
        model = imagenJuegoFavorito,
        contentDescription = null,
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.dimen_16dp),
                top = dimensionResource(id = R.dimen.dimen_16dp),
                bottom = dimensionResource(id = R.dimen.dimen_16dp),
            )
            .size(dimensionResource(id = R.dimen.dimen_120dp)),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun ComponenteDatosJuegoFavorito(juegoFavorito: JuegoFavorito, modifier: Modifier) {
    val openSans = FontFamily(Font(R.font.open_sans))
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = juegoFavorito.titulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = openSans,
            color = Color.Black,
            maxLines = 1,
        )

        Text(
            text = juegoFavorito.desarrollador,
            fontSize = 14.sp,
            fontFamily = openSans,
            maxLines = 1,
            modifier = modifier.padding(
                top = dimensionResource(id = R.dimen.dimen_4dp),
                bottom = dimensionResource(id = R.dimen.dimen_4dp),
            ),
        )
    }
}

@Composable
fun ComponenteIconoRemoverFavorito(
    juegoFavorito: JuegoFavorito,
    juegoFavoritoViewModel: JuegoFavoritoViewModel,
    modifier: Modifier,
) {
    val contexto = LocalContext.current
    Icon(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.dimen_40dp))
            .padding(
                end = dimensionResource(id = R.dimen.dimen_16dp),
                top = dimensionResource(id = R.dimen.dimen_16dp),
            )
            .clickable {
                juegoFavoritoViewModel.removerJuegoFavorito(juegoFavorito)
                Toast.makeText(
                    contexto,
                    R.string.pantalla_favoritos_boton_remover_favoritos,
                    Toast.LENGTH_SHORT,
                ).show()
            },
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        tint = Color.Red,
    )
}
