package com.cristian.sistecreditotestapp.listarjuegos.datos.di

import com.cristian.sistecreditotestapp.listarjuegos.datos.api.JuegoApi
import com.cristian.sistecreditotestapp.listarjuegos.datos.repositorios.JuegosRepositorioImpl
import com.cristian.sistecreditotestapp.listarjuegos.dominio.casosdeuso.ObtenerJuegosUC
import com.cristian.sistecreditotestapp.listarjuegos.dominio.repositorios.JuegosRepositorio
import com.cristian.sistecreditotestapp.listarjuegos.presentacion.viewmodel.JuegosViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ModuloListarJuegos {

    @Provides
    fun juegosViewModel(
        obtenerJuegosUC: ObtenerJuegosUC,
    ): JuegosViewModel = JuegosViewModel(obtenerJuegosUC = obtenerJuegosUC)

    @Provides
    @ViewModelScoped
    fun obtenerJuegosUC(
        juegosRepositorio: JuegosRepositorio,
    ): ObtenerJuegosUC = ObtenerJuegosUC(juegosRepositorio)

    @Provides
    @ViewModelScoped
    fun juegoRepositorio(
        juegoApi: JuegoApi,
    ): JuegosRepositorio = JuegosRepositorioImpl(juegoApi = juegoApi)

    @Provides
    @ViewModelScoped
    fun proveerJuegoApi(retrofit: Retrofit): JuegoApi {
        return retrofit.create(JuegoApi::class.java)
    }
}
