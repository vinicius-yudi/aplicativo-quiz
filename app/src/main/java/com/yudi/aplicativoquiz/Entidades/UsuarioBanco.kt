package com.yudi.aplicativoquiz.Entidades

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Usuario::class], version = 1)
abstract class UsuarioBanco : RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao

    companion object{
        @Volatile
        private var INSTANCE: UsuarioBanco? = null

        fun getBanco(context: Context): UsuarioBanco {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioBanco::class.java,
                    "usuario_banco"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}