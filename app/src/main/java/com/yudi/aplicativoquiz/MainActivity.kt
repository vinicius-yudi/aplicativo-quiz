package com.yudi.aplicativoquiz
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yudi.aplicativoquiz.telas.Menu
import com.yudi.aplicativoquiz.telas.TelaLeaderBoard
import com.yudi.aplicativoquiz.telas.TelaQuiz
import com.yudi.aplicativoquiz.ui.theme.AplicativoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicativoQuizTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.menu
                ) {
                    composable(Routes.menu) { Menu(navController = navController) }
                    composable(Routes.quiz) { TelaQuiz(navController = navController) }
                    composable(Routes.leaderboard) { TelaLeaderBoard(navController = navController) }
                }
            }
        }
    }
}
