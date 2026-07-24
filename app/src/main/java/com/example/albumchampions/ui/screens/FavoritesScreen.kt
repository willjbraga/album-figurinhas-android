// ui/screens/FavoritesScreen.kt
package com.example.albumchampions.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.data.model.Coach
import com.example.albumchampions.data.model.Player
import com.example.albumchampions.viewmodel.CoachViewModel
import com.example.albumchampions.viewmodel.PlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onPlayerClick: (String) -> Unit,
    onCoachClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("AlbumPrefs", Context.MODE_PRIVATE) }

    val allEntries = sharedPreferences.all
    val favoritePlayers = allEntries
        .filter { it.key.startsWith("fav_player_") && it.value == true }
        .map { it.key.removePrefix("fav_player_") }

    val favoriteCoaches = allEntries
        .filter { it.key.startsWith("fav_coach_") && it.value == true }
        .mapNotNull { it.key.removePrefix("fav_coach_").toIntOrNull() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MEUS FAVORITOS", color = Color.White, fontFamily = FrauncesFont, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF03071E))
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF03071E))
                .padding(padding)
        ) {
            if (favoritePlayers.isEmpty() && favoriteCoaches.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.Star, contentDescription = null, tint = Color.White.copy(alpha = 0.3f), modifier = Modifier.size(64.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Nenhuma figurinha favoritada", color = Color.White.copy(alpha = 0.5f), fontFamily = FrauncesFontL, fontSize = 16.sp)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    if (favoriteCoaches.isNotEmpty()) {
                        item(span = { GridItemSpan(3) }) {
                            Text(
                                text = "TREINADORES",
                                color = Color(0xFFFFD700),
                                fontFamily = FrauncesFontR,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                        items(favoriteCoaches, span = { GridItemSpan(3) }) { teamId ->
                            FavoriteCoachItem(teamId = teamId, onClick = { onCoachClick(teamId) })
                        }
                    }

                    if (favoritePlayers.isNotEmpty()) {
                        item(span = { GridItemSpan(3) }) {
                            Text(
                                text = "JOGADORES",
                                color = Color(0xFFFFD700),
                                fontFamily = FrauncesFontR,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                            )
                        }
                        items(favoritePlayers) { playerName ->
                            FavoritePlayerItem(playerName = playerName, onClick = { onPlayerClick(playerName) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoritePlayerItem(playerName: String, onClick: () -> Unit) {
    val viewModel: PlayerViewModel = viewModel(key = "fav_player_$playerName")
    LaunchedEffect(playerName) { viewModel.loadPlayer(playerName) }

    val player by viewModel.player.collectAsState()
    val team by viewModel.team.collectAsState()

    if (player != null && team != null) {
        FavoritePlayerGridCard(
            player = player!!,
            badgeColor = team!!.corPrimaria.toComposeColor(),
            onClick = onClick
        )
    } else {
        Box(
            modifier = Modifier.fillMaxWidth().height(165.dp).clip(RoundedCornerShape(8.dp)).background(Color.White.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFFFFD700), modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
        }
    }
}

@Composable
fun FavoriteCoachItem(teamId: Int, onClick: () -> Unit) {
    val viewModel: CoachViewModel = viewModel(key = "fav_coach_$teamId")
    LaunchedEffect(teamId) { viewModel.loadCoach(teamId) }

    val coach by viewModel.coach.collectAsState()

    if (coach != null) {
        FavoriteCoachCard(coach = coach!!, onClick = onClick)
    } else {
        Box(
            modifier = Modifier.fillMaxWidth().height(95.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFFFFD700), modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
        }
    }
}

@Composable
fun FavoritePlayerGridCard(player: Player, badgeColor: Color, onClick: () -> Unit) {
    val isStar = player.estrela
    val containerBg = if (isStar) Color(0xFFFFF7C2) else Color.White
    val borderColor = if (isStar) Color(0xFFFFD700) else badgeColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(2.dp, borderColor, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = containerBg)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
                Image(
                    painter = painterResource(id = player.fotoResId),
                    contentDescription = "Foto ${player.nome}",
                    modifier = Modifier.fillMaxWidth().height(125.dp).clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)),
                    contentScale = ContentScale.Crop
                )

                // Número do jogador — texto e borda com contraste automático,
                // igual ao ajuste feito no TeamAlbumScreen, para não sumir quando
                // a cor dinâmica do time for clara (branco/amarelo claro).
                val badgeIsLight = badgeColor.luminance() > 0.5f
                val numberTextColor = if (badgeIsLight) Color.Black else Color.White
                val badgeBorderColor = if (badgeIsLight) Color.Black.copy(alpha = 0.25f) else Color.Black.copy(alpha = 0.15f)

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(22.dp)
                        .background(badgeColor, CircleShape)
                        .border(1.dp, badgeBorderColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${player.numCamisa}", color = numberTextColor, fontSize = 11.sp, fontFamily = FrauncesFont, fontWeight = FontWeight.Bold)
                }

                if (isStar) {
                    Icon(
                        imageVector = Icons.Filled.Star, contentDescription = "Estrela", tint = Color(0xFFFFD700),
                        modifier = Modifier.align(Alignment.TopEnd).padding(4.dp).size(18.dp)
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = player.nome.uppercase(), color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center, fontFamily = FrauncesFont, maxLines = 2, lineHeight = 11.sp
                )
            }
        }
    }
}

@Composable
fun FavoriteCoachCard(coach: Coach, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = coach.fotoResId),
                contentDescription = "Foto ${coach.nome}",
                modifier = Modifier.width(110.dp).height(95.dp).clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                contentScale = ContentScale.Crop,
                // Mesmo ajuste aplicado no TeamAlbumScreen: ancora o corte pelo topo da foto
                // para não cortar a cabeça do treinador. Usando -1f (topo total), como você
                // ajustou por lá. Se ficar "top demais" aqui, é só reduzir um pouco (ex.: -0.8f).
                alignment = BiasAlignment(horizontalBias = 0f, verticalBias = -1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = coach.nome.uppercase(), color = Color.Black, fontSize = 15.sp, fontFamily = FrauncesFont, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                // <-- EMOJI ADICIONADO AQUI!
                Text(text = "${obterBandeiraEmojiFav(coach.pais)} ${coach.pais.uppercase()}", color = Color.Black.copy(alpha = 0.7f), fontSize = 13.sp, fontFamily = FrauncesFontL)
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Ver detalhes",
                tint = Color.Black.copy(alpha = 0.7f), modifier = Modifier.padding(16.dp).size(28.dp)
            )
        }
    }
}

// ── FUNÇÃO DE BANDEIRAS PARA A TELA DE FAVORITOS ──
private fun obterBandeiraEmojiFav(pais: String): String {
    return when (pais.lowercase().trim()) {
        "alemanha" -> "🇩🇪"
        "espanha" -> "🇪🇸"
        "portugal" -> "🇵🇹"
        "itália", "italia" -> "🇮🇹"
        "frança", "franca" -> "🇫🇷"
        "inglaterra" -> "🏴󠁧󠁢󠁥󠁮󠁧󠁿"
        "brasil" -> "🇧🇷"
        "argentina" -> "🇦🇷"
        "holanda", "países baixos" -> "🇳🇱"
        "croácia", "croacia" -> "🇭🇷"
        "geórgia", "georgia" -> "🇬🇪"
        "polônia", "polonia" -> "🇵🇱"
        "colômbia", "colombia" -> "🇨🇴"
        "rússia", "russia" -> "🇷🇺"
        "uruguai" -> "🇺🇾"
        "senegal" -> "🇸🇳"
        "bélgica", "belgica" -> "🇧🇪"
        "estados unidos", "eua" -> "🇺🇸"
        else -> "🌍"
    }
}