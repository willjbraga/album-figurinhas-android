// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/CoachScreen.kt
// Tela de detalhes do treinador
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.data.remote.FotoMap
import com.example.albumchampions.viewmodel.CoachViewModel

@Composable
fun CoachScreen(
    teamId: Int,
    onBackClick: () -> Unit,
    viewModel: CoachViewModel = viewModel()
) {
    LaunchedEffect(teamId) { viewModel.loadCoach(teamId) }

    val coach by viewModel.coach.collectAsState()
    val team by viewModel.team.collectAsState()

    val primaryColor = team?.corPrimaria?.toComposeColor() ?: Color(0xFF1A237E)
    val secondaryColor = team?.corSecundaria?.toComposeColor() ?: Color(0xFF0A0E27)

    val corTextoPrincipal = if (secondaryColor.luminance() > 0.5f) Color(0xFF0A0E27) else Color.White

    var isFavorite by remember { mutableStateOf(false) }

    coach?.let { c ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(secondaryColor)
        ) {
            // CAMADA DE ESMAECIMENTO DA TELA
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.65f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                // 1. Barra Superior
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onBackClick() }
                    )

                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Favoritar",
                        tint = if (isFavorite) Color(0xFFFFD700) else Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { isFavorite = !isFavorite }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 2. Header: Foto e Informações
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(210.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(primaryColor)
                            .background(Color.Black.copy(alpha = 0.25f)) // <-- ESMAECIMENTO DO FUNDO DA FOTO AQUI!
                    ) {
                        Image(
                            painter = painterResource(id = c.fotoResId),
                            contentDescription = "Foto ${c.nome}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Column {
                        Text(
                            text = c.nome.uppercase(),
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 26.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        team?.let {
                            Text(
                                text = it.nome.uppercase(),
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${obterBandeiraEmoji(c.pais)} ${c.pais.uppercase()}",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 3. Perfil
                Text(
                    text = "PERFIL",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = c.perfil.replace("\n", " ").replace("  ", " "),
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 4. Linhas de Informações
                InfoWhiteRow(icon = obterBandeiraEmoji(c.pais), label = "PAÍS", value = c.pais)
                Spacer(modifier = Modifier.height(8.dp))

                val escudoDoTime = painterResource(id = FotoMap.timeFoto(team?.nome ?: ""))
                InfoWhiteRowImage(painter = escudoDoTime, label = "TIME ATUAL", value = team?.nome ?: "")
                Spacer(modifier = Modifier.height(8.dp))

                InfoWhiteRow(icon = "💬", label = "IDIOMA", value = c.idioma)

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    } ?: run {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}