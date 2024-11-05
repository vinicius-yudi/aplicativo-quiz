import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yudi.aplicativoquiz.Menu
import com.yudi.aplicativoquiz.TelaLeadBoard
import com.yudi.aplicativoquiz.TelaQuiz
import com.yudi.aplicativoquiz.ui.theme.AplicativoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicativoQuizTheme {
                // Inicialize o NavController
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "menu") {
                    composable("menu") {
                        Menu(
                            onStartQuiz = { navController.navigate("quiz") },
                            onViewLeaderboard = { navController.navigate("leaderboard") }
                        )
                    }
                    composable("quiz") { TelaQuiz() }
                    composable("leaderboard") { TelaLeadBoard() }
                }
            }
        }
    }

    @Composable
    @Preview
    fun Preview() {
        Menu(
            onStartQuiz = {},
            onViewLeaderboard = {}
        )
    }

}
