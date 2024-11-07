package com.yudi.aplicativoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.yudi.aplicativoquiz.data.AppDatabase
import com.yudi.aplicativoquiz.data.Leaderboard
import com.yudi.aplicativoquiz.telas.Menu
import com.yudi.aplicativoquiz.telas.TelaFinalizacao
import com.yudi.aplicativoquiz.telas.TelaLeaderBoard
import com.yudi.aplicativoquiz.telas.TelaQuiz
import com.yudi.aplicativoquiz.ui.theme.AplicativoQuizTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leaderboard-db"
        ).build()

        setContent {
            AplicativoQuizTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.menu
                ) {
                    composable(Routes.menu) { Menu(navController = navController) }
                    composable(Routes.quiz) { TelaQuiz(navController = navController) }
                    composable(Routes.leaderboard) {
                        TelaLeaderBoard(navController = navController, db = db)
                    }
                    composable(
                        Routes.finalizacao,
                        arguments = listOf(navArgument("pontuacao") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val pontuacao = backStackEntry.arguments?.getInt("pontuacao") ?: 0
                        TelaFinalizacao(
                            navController = navController,
                            pontuacao = pontuacao,
                            db = db
                        ) { nome ->
                            lifecycleScope.launch {
                                val leaderboard = Leaderboard(nome = nome, pontuacao = pontuacao)
                                db.leaderboardDao().insert(leaderboard)
                            }
                        }
                    }
                }
            }
        }
    }
}