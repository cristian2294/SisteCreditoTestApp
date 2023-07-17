package com.cristian.sistecreditotestapp.detallejuego.datos.di

import com.cristian.sistecreditotestapp.detallejuego.datos.api.DetalleJuegoApi
import com.cristian.sistecreditotestapp.detallejuego.datos.repositorios.DetalleJuegoRepositorioImpl
import com.cristian.sistecreditotestapp.detallejuego.dominio.casosdeuso.ObtenerDetalleJuegoUC
import com.cristian.sistecreditotestapp.detallejuego.dominio.repositorios.DetalleJuegoRepositorio
import com.cristian.sistecreditotestapp.detallejuego.presentacion.viewmodel.DetalleJuegoViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ModuloDetalleJuego {

    @Provides
    fun detalleJuegoViewModel(
        obtenerDetalleJuegoUC: ObtenerDetalleJuegoUC,
    ): DetalleJuegoViewModel = DetalleJuegoViewModel(
        obtenerDetalleJuegoUC = obtenerDetalleJuegoUC,
    )

    @Provides
    @ViewModelScoped
    fun obtenerDetalleJuegoUC(
        detalleJuegoRepositorio: DetalleJuegoRepositorio,
    ): ObtenerDetalleJuegoUC = ObtenerDetalleJuegoUC(
        detalleJuegoRepositorio = detalleJuegoRepositorio,
    )

    @Provides
    @ViewModelScoped
    fun detalleJuegoRepositorio(
        detalleJuegoApi: DetalleJuegoApi,
    ): DetalleJuegoRepositorio = DetalleJuegoRepositorioImpl(detalleJuegoApi = detalleJuegoApi)

    @Provides
    @ViewModelScoped
    fun proveerDetalleJuegoApi(retrofit: Retrofit): DetalleJuegoApi {
        return retrofit.create(DetalleJuegoApi::class.java)
    }
}
