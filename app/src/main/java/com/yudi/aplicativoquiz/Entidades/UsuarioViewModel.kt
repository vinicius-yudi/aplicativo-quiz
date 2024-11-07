package com.yudi.aplicativoquiz.Entidades

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {
    private val UsuarioDao = UsuarioBanco.getBanco(application).UsuarioDao()

    fun saveScore(nome: String, pontuacao: Int) {
        val score = Usuario(nome = nome, pontuacao = pontuacao)
        viewModelScope.launch {
            try {
                UsuarioDao.insert(score)
                Log.d("UsuarioViewModel", "Pontuação salva com sucesso!")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("UsuarioViewModel", "Erro ao salvar pontuação", e)
            }
        }
    }
}