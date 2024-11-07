package com.yudi.aplicativoquiz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard")
data class Leaderboard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val pontuacao: Int
)