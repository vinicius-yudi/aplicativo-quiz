package com.yudi.aplicativoquiz.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Leaderboard::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leaderboardDao(): LeaderboardDao
}