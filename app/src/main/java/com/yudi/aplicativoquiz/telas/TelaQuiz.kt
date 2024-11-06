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

    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.belize,
        opcoesResposta = listOf("Belize", "Bahamas", "Barbados", "Bolívia"),
        respostaCorreta = "Belize"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.butao,
        opcoesResposta = listOf("Butão", "Nepal", "Sri Lanka", "Mongólia"),
        respostaCorreta = "Butão"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.suriname,
        opcoesResposta = listOf("Suriname", "Gana", "Guiana", "Paraguai"),
        respostaCorreta = "Suriname"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.uzbequistao,
        opcoesResposta = listOf("Uzbequistão", "Cazaquistão", "Turcomenistão", "Tadjiquistão"),
        respostaCorreta = "Uzbequistão"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.angola,
        opcoesResposta = listOf("Angola", "Moçambique", "Guiné-Bissau", "Cabo Verde"),
        respostaCorreta = "Angola"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.malawi,
        opcoesResposta = listOf("Malawi", "Zâmbia", "Quênia", "Ruanda"),
        respostaCorreta = "Malawi"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.papua_nova_guine,
        opcoesResposta = listOf("Papua Nova Guiné", "Timor-Leste", "Indonésia", "Filipinas"),
        respostaCorreta = "Papua Nova Guiné"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.zimbabue,
        opcoesResposta = listOf("Zimbábue", "Zâmbia", "Botsuana", "Malaui"),
        respostaCorreta = "Zimbábue"
    ),

    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.indonesia,
        opcoesResposta = listOf("Indonésia", "Mônaco", "Polônia", "Singapura"),
        respostaCorreta = "Indonésia"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.san_marino,
        opcoesResposta = listOf("San Marino", "Eslováquia", "Grécia", "Eslovênia"),
        respostaCorreta = "San Marino"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.austria,
        opcoesResposta = listOf("Áustria", "Letônia", "Dinamarca", "Alemanha"),
        respostaCorreta = "Áustria"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.australia,
        opcoesResposta = listOf("Austrália", "Nova Zelândia", "Reino Unido", "Fiji"),
        respostaCorreta = "Austrália"
    ),
    Pergunta(
        textoPergunta = "Esta bandeira representa qual país?",
        imagemId = R.drawable.granada,
        opcoesResposta = listOf("Granada", "Jamaica", "Barbados", "Santa Lúcia"),
        respostaCorreta = "Granada"
    )
)

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun TelaQuiz(navController: NavController) {
    var perguntasEmbaralhadas by remember { mutableStateOf(perguntas.shuffled()) } // Lista de perguntas embaralhadas
    var perguntaAtual by remember { mutableStateOf(0) }
    var pontuacao by remember { mutableStateOf(0) }
    var tempoInicial by remember { mutableStateOf(System.currentTimeMillis()) }
    val pergunta = perguntasEmbaralhadas[perguntaAtual] // Usa a pergunta embaralhada
    val opcoesEmbaralhadas = pergunta.opcoesResposta.shuffled()
    var respostaSelecionada by remember { mutableStateOf<String?>(null) }
    var respostaCorretaSelecionada by remember { mutableStateOf(false) }
    var esperandoPorProximaPergunta by remember { mutableStateOf(false) }

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
            fadeIn(animationSpec = tween(1000)) with fadeOut(animationSpec = tween(1000))
        }) {
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
                    val escala by animateFloatAsState(if (clicado) 1.1f else 1f)

                    Button(
                        onClick = {
                            clicado = true
                            respostaSelecionada = opcao
                            respostaCorretaSelecionada = isCorreta
                            val tempoResposta = System.currentTimeMillis() - tempoInicial
                            val pontos = calcularPontuacao(tempoResposta, isCorreta)
                            pontuacao += pontos

                            respostaSelecionada = null
                            if (perguntaAtual < perguntasEmbaralhadas.size - 1) {
                                perguntaAtual++
                                tempoInicial = System.currentTimeMillis()
                            } else {
                                navController.navigate("finalizacao/${pontuacao}")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = when {
                                clicado && respostaCorretaSelecionada -> Color(0xFF4CAF50)
                                clicado && !respostaCorretaSelecionada -> Color(0xFFD32F2F)
                                else -> Color(0xFF6200EE)
                            }
                        ),
                        modifier = Modifier
                            .scale(escala)
                            .width(250.dp)
                            .height(60.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = opcao)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                AnimatedContent(targetState = pontuacao) { pontuacaoAnimada ->
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
