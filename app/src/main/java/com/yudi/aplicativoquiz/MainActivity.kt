package com.yudi.aplicativoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yudi.aplicativoquiz.Entidades.UsuarioViewModel
import com.yudi.aplicativoquiz.telas.Menu
import com.yudi.aplicativoquiz.telas.TelaFinalizacao
import com.yudi.aplicativoquiz.telas.TelaLeaderBoard
import com.yudi.aplicativoquiz.telas.TelaQuiz
import com.yudi.aplicativoquiz.ui.theme.AplicativoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicativoQuizTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.menu
                ) {
                    composable(Routes.menu) { Menu(navController = navController) }
                    composable(Routes.quiz) { TelaQuiz(navController = navController) }
                    composable(
                        "leaderboard/{nome}/{pontuacao}",
                        arguments = listOf(
                            navArgument("nome") { type = NavType.StringType },
                            navArgument("pontuacao") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val nome = backStackEntry.arguments?.getString("nome") ?: ""
                        val pontuacao = backStackEntry.arguments?.getInt("pontuacao") ?: 0
                        val usuarioViewModel: UsuarioViewModel = viewModel()
                        TelaLeaderBoard(
                            nome = nome,
                            pontuacao = pontuacao,
                            usuarioViewModel = usuarioViewModel
                        )
                    }
                    composable(
                        Routes.finalizacao,
                        arguments = listOf(navArgument("pontuacao") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val pontuacao = backStackEntry.arguments?.getInt("pontuacao") ?: 0
                        TelaFinalizacao(navController = navController, pontuacao = pontuacao) { nome ->
                            // Lógica para salvar o nome ou outras ações
                        }
                    }
                }
            }
        }
    }
}
