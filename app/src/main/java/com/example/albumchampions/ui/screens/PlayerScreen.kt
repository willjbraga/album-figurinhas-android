// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/PlayerScreen.kt
// Tela de detalhes do jogador
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.viewmodel.PlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(
    playerName: String,
    onBackClick: () -> Unit,
    viewModel: PlayerViewModel = viewModel()
) {
    LaunchedEffect(playerName) { viewModel.loadPlayer(playerName) }

    val player by viewModel.player.collectAsState()
    val team   by viewModel.team.collectAsState()

    val primaryColor   = team?.corPrimaria?.toComposeColor()   ?: Color(0xFF1A237E)
    val secondaryColor = team?.corSecundaria?.toComposeColor() ?: Color(0xFF0A0E27)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jogador", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryColor)
            )
        }
    ) { padding ->
        player?.let { p ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(secondaryColor)
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto
                Image(
                    painter = painterResource(id = p.fotoResId),
                    contentDescription = "Foto ${p.nome}",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))

                // Nome + estrela
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(p.nome, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    if (p.estrela) {
                        Spacer(Modifier.width(8.dp))
                        Icon(Icons.Default.Star, contentDescription = "Estrela", tint = Color(0xFFFFD700), modifier = Modifier.size(22.dp))
                    }
                }

                Text("#${p.numCamisa}  •  ${p.pais}", color = Color.White.copy(alpha = 0.65f), fontSize = 14.sp)
                Spacer(Modifier.height(4.dp))
                team?.let {
                    Text(it.nome, color = Color(0xFFFFD700), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(24.dp))

                // Estatísticas
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = primaryColor.copy(alpha = 0.25f))
                ) {
                    Text(
                        "Estatísticas",
                        color = Color(0xFFFFD700),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(label = "Partidas", value = "${p.partidas}")
                        StatItem(label = "Gols", value = "${p.gols}")
                        StatItem(label = "Assistências", value = "${p.assistencia}")
                    }
                    Spacer(Modifier.height(8.dp))
                }
                Spacer(Modifier.height(12.dp))

                // Sobre
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = primaryColor.copy(alpha = 0.2f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Sobre", color = Color(0xFFFFD700), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(p.sobre, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp, lineHeight = 22.sp)
                    }
                }
            }
        } ?: run {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Text(label, color = Color.White.copy(alpha = 0.65f), fontSize = 12.sp)
    }
}