package com.yudi.aplicativoquiz.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LeaderboardDao {
    @Insert
    suspend fun insert(leaderboard: Leaderboard)

    @Query("SELECT * FROM leaderboard ORDER BY pontuacao DESC")
    suspend fun getAllScores(): List<Leaderboard>
}
