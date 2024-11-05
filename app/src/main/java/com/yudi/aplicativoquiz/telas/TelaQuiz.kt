@file:OptIn(ExperimentalAnimationApi::class)

package com.yudi.aplicativoquiz.telas

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yudi.aplicativoquiz.R

data class Pergunta(
    val textoPergunta: String,
    val imagemId: Int,
    val opcoesResposta: List<String>,
    val respostaCorreta: String
)

val perguntas = listOf(
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.brasil,
        opcoesResposta = listOf("Brasil", "Chile", "Peru", "Camarões"),
        respostaCorreta = "Brasil"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.chile,
        opcoesResposta = listOf("China", "Coreia do Sul", "Chile", "Tailândia"),
        respostaCorreta = "Chile"
    ),
    // Adicione mais perguntas conforme necessário
)

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun TelaQuiz(navController: NavController) {
    var perguntaAtual by remember { mutableStateOf(0) }
    var pontuacao by remember { mutableStateOf(0) }
    var tempoInicial by remember { mutableStateOf(System.currentTimeMillis()) }
    var respostaSelecionada by remember { mutableStateOf<String?>(null) }

    val pergunta = perguntas[perguntaAtual]
    val opcoesEmbaralhadas = pergunta.opcoesResposta.shuffled()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        AnimatedContent(targetState = perguntaAtual, transitionSpec = {
            fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
        }) { // Animação de transição entre perguntas
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = pergunta.imagemId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(20.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = pergunta.textoPergunta,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                opcoesEmbaralhadas.forEach { opcao ->
                    val isCorreta = opcao == pergunta.respostaCorreta
                    var clicado by remember { mutableStateOf(false) }
                    val escala by animateFloatAsState(if (clicado) 1.1f else 1f) // Animação de clique no botão

                    Button(
                        onClick = {
                            clicado = true
                            respostaSelecionada = opcao
                            val tempoResposta = System.currentTimeMillis() - tempoInicial
                            val pontos = calcularPontuacao(tempoResposta, isCorreta)
                            pontuacao += pontos

                            if (isCorreta) {
                                respostaSelecionada = null
                                if (perguntaAtual < perguntas.size - 1) {
                                    perguntaAtual++
                                    tempoInicial = System.currentTimeMillis()
                                } else {
                                    // Finalizar o quiz ou reiniciar
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (respostaSelecionada == opcao && isCorreta) Color(0xFF4CAF50) else Color(0xFF6200EE) // Verde se correto
                        ),
                        modifier = Modifier
                            .scale(escala) // Animação de escala no botão
                            .width(250.dp)
                            .height(60.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = opcao)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                AnimatedContent(targetState = pontuacao) { pontuacaoAnimada -> // Animação na pontuação
                    Text(
                        text = "Pontuação: $pontuacao",
                        color = Color.White
                    )
                }
            }
        }
    }
}

fun calcularPontuacao(tempoResposta: Long, respostaCorreta: Boolean): Int {
    if (!respostaCorreta) return 0

    return when {
        tempoResposta < 5000 -> 10
        tempoResposta < 10000 -> 5
        else -> 2
    }
}
