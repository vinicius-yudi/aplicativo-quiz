package com.yudi.aplicativoquiz.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yudi.aplicativoquiz.R
import com.yudi.aplicativoquiz.Routes

@Composable
fun TelaFinalizacao(
    navController: NavController,
    pontuacao: Int,
    onSave: (String) -> Unit
) {
    var nome by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Parabéns!")
        Text(text = "Sua pontuação: $pontuacao")

        Image(
            painter = painterResource(id = R.drawable.trofeu),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Seu Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nome.isNotBlank()) {
                onSave(nome)
                // Navega para a tela LeaderBoard
                navController.navigate(Routes.leaderboard) {
                    // Limpa o histórico para evitar que o usuário volte para a tela de finalização ao pressionar "voltar"
                    popUpTo(Routes.menu) { inclusive = false }
                }
            }
        }) {
            Text(text = "Salvar")
        }
    }
}
