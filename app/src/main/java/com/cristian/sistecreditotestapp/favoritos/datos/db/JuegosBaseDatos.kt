package com.cristian.sistecreditotestapp.favoritos.datos.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cristian.sistecreditotestapp.favoritos.datos.db.dao.JuegoFavoritoDAO
import com.cristian.sistecreditotestapp.favoritos.datos.db.entidades.JuegoFavoritoEntidad

@Database(entities = [JuegoFavoritoEntidad::class], version = 1)
abstract class JuegosBaseDatos : RoomDatabase() {
    abstract fun juegosFavoritoDAO(): JuegoFavoritoDAO
}
