package com.yudi.aplicativoquiz.Entidades

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface UsuarioDao {
    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuario ORDER BY pontuacao DESC")
    suspend fun getAllScores(): List<Usuario>

    @Query("SELECT * FROM usuario ORDER BY pontuacao DESC LIMIT 1")
    suspend fun getHighScore(): Usuario?

}