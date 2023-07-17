package com.cristian.sistecreditotestapp.favoritos.presentacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso.AgregarJuegoFavoritoUC
import com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso.ObtenerJuegosFavoritosUC
import com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso.RemoverJuegoFavoritoUC
import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.presentacion.estados.EstadoJuegoFavorito
import com.cristian.sistecreditotestapp.favoritos.presentacion.estados.EstadoJuegoFavorito.Exitoso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JuegoFavoritoViewModel @Inject constructor(
    private val agregarJuegoFavoritoUC: AgregarJuegoFavoritoUC,
    private val removerJuegoFavoritoUC: RemoverJuegoFavoritoUC,
    obtenerJuegosFavoritosUC: ObtenerJuegosFavoritosUC,
) : ViewModel() {

    private val _mostrarDialogo = MutableStateFlow(false)
    val mostrarDialogo: StateFlow<Boolean> get() = _mostrarDialogo

    val estadoJuegoFavorito: StateFlow<EstadoJuegoFavorito> =
        obtenerJuegosFavoritosUC.invoke().map(::Exitoso)
            .catch {
                EstadoJuegoFavorito.Error(it)
                _mostrarDialogo.value = true
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                EstadoJuegoFavorito.Cargando,
            )

    fun agregarJuegoFavorito(juegoFavorito: JuegoFavorito) {
        viewModelScope.launch {
            agregarJuegoFavoritoUC.invoke(juegoFavorito)
        }
    }

    fun removerJuegoFavorito(juegoFavorito: JuegoFavorito) {
        viewModelScope.launch {
            removerJuegoFavoritoUC.invoke(juegoFavorito)
        }
    }

    fun cerrarDialogoError() {
        _mostrarDialogo.value = false
    }
}
