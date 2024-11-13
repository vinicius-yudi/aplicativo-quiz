package com.yudi.aplicativoquiz.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yudi.aplicativoquiz.R
import com.yudi.aplicativoquiz.Routes

@Composable
fun Menu(navController: NavController) {
    var isDialogOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bem-vindo ao Quiz App",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(top = 100.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.quizz),
                contentDescription = "Imagem do Quiz",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(1.0f),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier.padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate(Routes.quiz) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Começar Quiz",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate(Routes.leaderboard) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF03DAC5)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Ver Leaderboard",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { isDialogOpen = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA500)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Regras",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }

        if (isDialogOpen) {
            AlertDialog(
                onDismissRequest = { isDialogOpen = false },
                title = {
                    Text(text = "Regras do Quiz", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                },
                text = {
                    Text(
                        text =  "1. Responda o mais rápido possível\n" +
                                "2. Acertou ou Errou o quiz avança\n"+
                                "3. A pontuação funciona assim:\n"+
                                "Respondeu dentro de 5s = 10 pontos \n"+
                                "Demorou mais de 5s = 5 pontos \n"+
                                "Passou de 10s = 2 pontos",

                        fontSize = 16.sp
                    )
                },
                confirmButton = {

                },
                modifier = Modifier.fillMaxWidth(0.8f)
                    .height(350.dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu() {
    Menu(navController = rememberNavController())
}
