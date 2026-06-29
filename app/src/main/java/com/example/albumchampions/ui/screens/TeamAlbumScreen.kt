// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/TeamAlbumScreen.kt
// Tela Álbum do Time – escudo, treinador, jogadores (estrela em destaque)
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.data.model.Coach
import com.example.albumchampions.data.model.Player
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.viewmodel.TeamViewModel
import com.example.albumchampions.ui.components.CoachCard
import com.example.albumchampions.ui.components.PlayerCard
import com.example.albumchampions.ui.components.StarPlayerCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamAlbumScreen(
    teamId: Int,
    onShieldClick: () -> Unit,
    onCoachClick: () -> Unit,
    onPlayerClick: (String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: TeamViewModel = viewModel()
) {
    LaunchedEffect(teamId) { viewModel.loadTeam(teamId) }

    val team      by viewModel.team.collectAsState()
    val players   by viewModel.players.collectAsState()
    val coach     by viewModel.coach.collectAsState()
    val starPlayer by viewModel.starPlayer.collectAsState()
    val isLoading  by viewModel.isLoading.collectAsState()

    // Cores do time vindas do banco
    val primaryColor   = team?.corPrimaria?.toComposeColor()   ?: Color(0xFF1A237E)
    val secondaryColor = team?.corSecundaria?.toComposeColor() ?: Color(0xFF0A0E27)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(team?.nome ?: "", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryColor)
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(secondaryColor)
                .padding(padding),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // ── Escudo (clicável → TeamDetail) ────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    team?.let {
                        Image(
                            painter = painterResource(id = it.escudoResId),
                            contentDescription = "Escudo ${it.nome} – toque para detalhes",
                            modifier = Modifier
                                .size(130.dp)
                                .clickable { onShieldClick() }
                        )
                    }
                }
            }

            // ── Jogador Estrela ────────────────────────────────────────────
            starPlayer?.let { star ->
                item {
                    Text(
                        "⭐ Jogador Destaque",
                        color = Color(0xFFFFD700),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    StarPlayerCard(player = star, teamColor = primaryColor) {
                        onPlayerClick(star.nome)
                    }
                }
            }

            // ── Treinador ─────────────────────────────────────────────────
            item {
                Text(
                    "Treinador",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
            coach?.let { c ->
                item { CoachCard(coach = c, onClick = onCoachClick) }
            }

            // ── Jogadores ─────────────────────────────────────────────────
            item {
                Text(
                    "Jogadores",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
            items(players.filter { !it.estrela }) { player ->
                PlayerCard(player = player) { onPlayerClick(player.nome) }
            }
        }
    }
}

// ── Extensão: String hex → Compose Color ─────────────────────────────────────
fun String.toComposeColor(): Color = try {
    Color(android.graphics.Color.parseColor(this))
} catch (e: Exception) {
    Color.Unspecified
}

