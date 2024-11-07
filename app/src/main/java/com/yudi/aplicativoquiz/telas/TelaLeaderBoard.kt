package com.yudi.aplicativoquiz.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.yudi.aplicativoquiz.data.AppDatabase
import com.yudi.aplicativoquiz.data.Leaderboard

@Composable
fun TelaLeaderBoard(navController: NavController, db: AppDatabase) {
    val leaderboardList = remember { mutableStateOf<List<Leaderboard>>(emptyList()) }

    // Carregar os melhores scores
    LaunchedEffect(Unit) {
        leaderboardList.value = db.leaderboardDao().getTopScores() // Aqui, estamos garantindo que o banco seja consultado de forma assíncrona
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Leaderboard",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Exibir os 5 melhores scores
        if (leaderboardList.value.isEmpty()) {
            Text(text = "Sem pontuações ainda", fontSize = 20.sp)
        } else {
            leaderboardList.value.forEach { leaderboard ->
                Text(
                    text =  "${leaderboard.nome} - ${leaderboard.pontuacao}",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
    }
}