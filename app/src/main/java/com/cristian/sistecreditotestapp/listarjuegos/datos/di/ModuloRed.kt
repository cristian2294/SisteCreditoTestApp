package com.cristian.sistecreditotestapp.listarjuegos.datos.di

import com.cristian.sistecreditotestapp.utils.Constants.Companion.URL_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloRed {
    @Singleton
    @Provides
    fun proveerRetrofit(): Retrofit {
        val baseUrl = URL_BASE
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
