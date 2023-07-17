package com.cristian.sistecreditotestapp.favoritos.datos.di

import android.content.Context
import androidx.room.Room
import com.cristian.sistecreditotestapp.favoritos.datos.db.JuegosBaseDatos
import com.cristian.sistecreditotestapp.utils.Constants.Companion.NOMBRE_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuloBaseDatos {
    @Singleton
    @Provides
    fun proveerBaseDeDatos(@ApplicationContext contexto: Context): JuegosBaseDatos {
        return Room.databaseBuilder(
            context = contexto,
            klass = JuegosBaseDatos::class.java,
            name = NOMBRE_DB,
        ).build()
    }
}
