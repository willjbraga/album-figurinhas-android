// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/TeamAlbumScreen.kt
// Tela Álbum do Time – escudo, treinador, jogadores (estrela em destaque)
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

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
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.data.model.Coach
import com.example.albumchampions.data.model.Player
import com.example.albumchampions.viewmodel.TeamViewModel

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
    val isLoading by viewModel.isLoading.collectAsState()

    // Estado do favorito para a estrela na TopBar
    var isFavorite by remember { mutableStateOf(false) }

    // Cores do time vindas do banco
    val primaryColor   = team?.corPrimaria?.toComposeColor()   ?: Color(0xFF1A237E)
    val secondaryColor = when (team?.nome?.lowercase()) {
        "barcelona" -> Color(0xFF3A0018)      // Azul escuro do Barcelona
        "bayern de munique", "bayern" -> Color(0xFF595959) // Ex: Azul/Marinho personalizado para o Bayern
        "real madrid" -> Color(0xFF594206)    // Ex: Grafite para o Real Madrid
        "milan" -> Color(0xFF000000)          // Ex: Vermelho/Preto fechado para o Milan
        "psg" -> Color(0xFF4C0E0A)            // Ex: Azul escuro PSG
        else -> team?.corSecundaria?.toComposeColor() ?: Color(0xFF03071E) // Cor padrão caso não seja nenhum dos acima
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = "Favorito",
                            tint = if (isFavorite) Color(0xFFFFD700) else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = secondaryColor)
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
            return@Scaffold
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .background(secondaryColor)
                .padding(padding), // Corrigido: sem 'paddingValues ='
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // ── Header do Time (Escudo + Informações) ─────────────────────
            item(span = { GridItemSpan(3) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    team?.let {
                        Image(
                            painter = painterResource(id = it.escudoResId),
                            contentDescription = "Escudo ${it.nome} – toque para detalhes",
                            modifier = Modifier
                                .size(110.dp)
                                .clickable { onShieldClick() }
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = team?.nome?.uppercase() ?: "",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "${team?.numVitoria ?: 0} TÍTULOS",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "SAIBA MAIS",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            modifier = Modifier
                                .clickable { onShieldClick() }
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                        )
                    }
                }
            }

            // ── Seção Treinador ───────────────────────────────────────────
            item(span = { GridItemSpan(3) }) {
                Text(
                    text = "TREINADOR",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }
            coach?.let { c ->
                item(span = { GridItemSpan(3) }) {
                    CoachCardCustom(coach = c, onClick = onCoachClick)
                }
            }

            // ── Seção Jogadores ───────────────────────────────────────────
            item(span = { GridItemSpan(3) }) {
                Text(
                    text = "JOGADORES",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            items(players) { player ->
                PlayerGridCard(
                    player = player,
                    badgeColor = primaryColor
                ) {
                    onPlayerClick(player.nome)
                }
            }

            // ── Legenda Jogador Estrela ───────────────────────────────────
            item(span = { GridItemSpan(3) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "JOGADOR ESTRELA",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}

// ── Componentes Personalizados ──────────────────────────────────────────────

@Composable
private fun CoachCardCustom(coach: Coach, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = coach.fotoResId),
                contentDescription = "Foto ${coach.nome}",
                modifier = Modifier
                    .width(110.dp)
                    .height(95.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = coach.nome.uppercase(),
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "🇩🇪 ${coach.pais.uppercase()}",
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Ver detalhes",
                tint = Color.Black.copy(alpha = 0.7f),
                modifier = Modifier
                    .padding(16.dp)
                    .size(28.dp)
            )
        }
    }
}

@Composable
private fun PlayerGridCard(
    player: Player,
    badgeColor: Color,
    onClick: () -> Unit
) {
    val isStar = player.estrela
    val containerBg = if (isStar) Color(0xFFFFF7C2) else Color.White
    val borderColor = if (isStar) Color(0xFFFFD700) else Color(0xFF1A237E)

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)),
                    contentScale = ContentScale.Crop
                )

                // Número do jogador
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(22.dp)
                        .background(badgeColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${player.numCamisa}",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Estrela se for destaque
                if (isStar) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Jogador Estrela",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                            .size(18.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = player.nome.uppercase(),
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    lineHeight = 11.sp
                )
            }
        }
    }
}

// ── Extensão: String hex → Compose Color ─────────────────────────────────────
fun String.toComposeColor(): Color = try {
    Color(this.toColorInt())
} catch (_: Exception) {
    Color.Unspecified
}