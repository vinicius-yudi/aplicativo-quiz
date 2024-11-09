package com.yudi.aplicativoquiz.telas

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yudi.aplicativoquiz.R
import com.yudi.aplicativoquiz.Routes
import com.yudi.aplicativoquiz.data.AppDatabase
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun ConfettiEffect(showConfetti: Boolean) {
    if (showConfetti) {
        val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta)
        val confettiCount = 50

        Canvas(modifier = Modifier.fillMaxSize()) {
            for (i in 0 until confettiCount) {
                val x = Random.nextFloat() * size.width
                val y = Random.nextFloat() * size.height
                val color = colors[Random.nextInt(colors.size)]
                drawCircle(color, radius = 10f, center = Offset(x, y))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaFinalizacao(
    navController: NavController,
    pontuacao: Int,
    db: AppDatabase,
    onSave: (String) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var showConfetti by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        showConfetti = true
    }

    val scaleAnim = rememberInfiniteTransition()
    val scale by scaleAnim.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    Image(
        painter = painterResource(id = R.drawable.fundo),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Parabéns!",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(bottom = 15.dp)
                .scale(scale)

        )

        Text(
            text = "Sua pontuação: $pontuacao Pontos",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 70.dp)
                .scale(scale)
        )


        Image(
            painter = painterResource(id = R.drawable.trofeu),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Seu Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (nome.isNotBlank()) {
                    onSave(nome)
                    navController.navigate(Routes.leaderboard) {
                        popUpTo(Routes.menu) { inclusive = false }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF03DAC5)
            )
        ) {
            Text(text = "Salvar")
        }
    }
    ConfettiEffect(showConfetti = showConfetti)

}