package com.yudi.aplicativoquiz.telas

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yudi.aplicativoquiz.R
import com.yudi.aplicativoquiz.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaFinalizacao(
    navController: NavController,
    pontuacao: Int,
    onSave: (String) -> Unit
) {
    var nome by remember { mutableStateOf("") }

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
            modifier = Modifier.padding(bottom = 15.dp)
        )

        Text(
            text = "Sua pontuação: $pontuacao Pontos",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 70.dp)
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

        // Campo de texto para o nome com altura adequada
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Seu Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp)), // Mantém o fundo arredondado
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent // Define o fundo transparente para não sobrepor o background
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
}


@Composable
@Preview
fun TelaFinalizacaoPreview() {
    TelaFinalizacao(navController = rememberNavController(), pontuacao = 10) {}
}
