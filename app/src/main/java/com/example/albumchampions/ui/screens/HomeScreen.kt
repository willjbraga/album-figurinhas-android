// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/HomeScreen.kt
// Tela Inicial – mostra a competição e a lista de times
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.R
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.viewmodel.CompetitionViewModel

@Composable
fun HomeScreen(
    onTeamClick: (Int) -> Unit,
    viewModel: CompetitionViewModel = viewModel()
) {
    val competition by viewModel.competition.collectAsState()
    val teams by viewModel.teams.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0E27)),  // fundo escuro Champions
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // ── Header da competição ──────────────────────────────────────────
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF1A237E), Color(0xFF0A0E27))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Troféu – substitua R.drawable.trofeu pelo nome do seu arquivo
                 //   Image(
                   //     painter = painterResource(id = R.drawable.trofeu),
                   //     contentDescription = "Troféu UEFA Champions League",
                   //     modifier = Modifier.size(120.dp),
                   //     contentScale = ContentScale.Fit
                //    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = competition?.nome ?: "UEFA Champions League",
                        color = Color(0xFFFFD700),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Edição ${competition?.anoCompeticao ?: ""}",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${competition?.estadio ?: ""} • ${competition?.cidadeSede ?: ""}",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 12.sp
                    )
                }
            }
        }

        // ── Título da seção ───────────────────────────────────────────────
        item {
            Text(
                text = "Equipes Participantes",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }

        // ── Lista de cards de times ───────────────────────────────────────
        if (isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFFFD700))
                }
            }
        } else {
            items(teams) { team ->
                TeamCard(team = team, onClick = { onTeamClick(team.id) })
            }
        }
    }
}

// ── Card de time ──────────────────────────────────────────────────────────────
@Composable
fun TeamCard(team: Team, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C2151))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Escudo
            Image(
                painter = painterResource(id = team.escudoResId),
                contentDescription = "Escudo ${team.nome}",
               modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = team.nome,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = team.pais,
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 13.sp
                )
            }
            // Vitórias
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${team.numVitoria}",
                    color = Color(0xFFFFD700),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = if (team.numVitoria == 1) "título" else "títulos",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 11.sp
                )
            }
        }
    }
}






