package com.cristian.sistecreditotestapp.favoritos.datos.db.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cristian.sistecreditotestapp.favoritos.dominio.modelos.JuegoFavorito

@Entity(tableName = "juegoFavorito")
data class JuegoFavoritoEntidad(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val titulo: String,
    @ColumnInfo(name = "thumbnail")
    val imagen: String,
    @ColumnInfo(name = "developer")
    val desarrollador: String,
) {
    fun aDominio(): JuegoFavorito {
        return JuegoFavorito(
            id = id,
            titulo = titulo,
            imagen = imagen,
            desarrollador = desarrollador,
        )
    }
}
