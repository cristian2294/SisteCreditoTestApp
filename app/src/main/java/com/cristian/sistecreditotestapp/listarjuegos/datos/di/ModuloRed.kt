package com.cristian.sistecreditotestapp.listarjuegos.datos.di

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
        val baseUrl = "https://www.freetogame.com/api/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
