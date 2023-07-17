package com.cristian.sistecreditotestapp.favoritos.datos.di

import com.cristian.sistecreditotestapp.favoritos.datos.db.JuegosBaseDatos
import com.cristian.sistecreditotestapp.favoritos.datos.db.dao.JuegoFavoritoDAO
import com.cristian.sistecreditotestapp.favoritos.datos.repositorios.JuegosLocalRepositorioImpl
import com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso.AgregarJuegoFavoritoUC
import com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso.ObtenerJuegosFavoritosUC
import com.cristian.sistecreditotestapp.favoritos.dominio.casosdeuso.RemoverJuegoFavoritoUC
import com.cristian.sistecreditotestapp.favoritos.dominio.repositorios.JuegosLocalRepositorio
import com.cristian.sistecreditotestapp.favoritos.presentacion.viewmodel.JuegoFavoritoViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ModuloJuegosFavoritos {

    @Provides
    fun juegoFavoritoViewModel(
        agregarJuegoFavoritoUC: AgregarJuegoFavoritoUC,
        removerJuegoFavoritoUC: RemoverJuegoFavoritoUC,
        obtenerJuegosFavoritosUC: ObtenerJuegosFavoritosUC,
    ): JuegoFavoritoViewModel = JuegoFavoritoViewModel(
        agregarJuegoFavoritoUC = agregarJuegoFavoritoUC,
        removerJuegoFavoritoUC = removerJuegoFavoritoUC,
        obtenerJuegosFavoritosUC = obtenerJuegosFavoritosUC,
    )

    @Provides
    @ViewModelScoped
    fun obtenerJuegoFavoritoUC(
        juegosLocalRepositorio: JuegosLocalRepositorio,
    ): ObtenerJuegosFavoritosUC =
        ObtenerJuegosFavoritosUC(juegosLocalRepositorio = juegosLocalRepositorio)

    @Provides
    @ViewModelScoped
    fun removerJuegoFavoritoUC(
        juegosLocalRepositorio: JuegosLocalRepositorio,
    ): RemoverJuegoFavoritoUC =
        RemoverJuegoFavoritoUC(juegosLocalRepositorio = juegosLocalRepositorio)

    @Provides
    @ViewModelScoped
    fun agregarJuegoFavoritoUC(
        juegosLocalRepositorio: JuegosLocalRepositorio,
    ): AgregarJuegoFavoritoUC =
        AgregarJuegoFavoritoUC(juegosLocalRepositorio = juegosLocalRepositorio)

    @Provides
    @ViewModelScoped
    fun juegosLocalRepositorio(
        juegoFavoritoDAO: JuegoFavoritoDAO,
    ): JuegosLocalRepositorio =
        JuegosLocalRepositorioImpl(juegoFavoritoDAO = juegoFavoritoDAO)

    @Provides
    @ViewModelScoped
    fun proveerJuegosFavoritosDAO(
        juegosBaseDatos: JuegosBaseDatos,
    ): JuegoFavoritoDAO =
        juegosBaseDatos.juegosFavoritoDAO()
}
