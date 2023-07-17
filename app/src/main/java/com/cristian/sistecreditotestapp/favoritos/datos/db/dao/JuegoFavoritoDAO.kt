package com.cristian.sistecreditotestapp.favoritos.datos.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cristian.sistecreditotestapp.favoritos.datos.db.entidades.JuegoFavoritoEntidad
import kotlinx.coroutines.flow.Flow

@Dao
interface JuegoFavoritoDAO {

    @Query("SELECT * FROM juegoFavorito ORDER BY id ASC")
    fun obtenerJuegosFavoritos(): Flow<List<JuegoFavoritoEntidad>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarJuegoFavorito(juego: JuegoFavoritoEntidad)

    @Delete
    suspend fun removerJuegoFavorito(juego: JuegoFavoritoEntidad)
}
