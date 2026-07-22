// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/PlayerScreen.kt
// Tela de detalhes do jogador
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.data.remote.FotoMap
import com.example.albumchampions.viewmodel.PlayerViewModel

@Composable
fun PlayerScreen(
    playerName: String,
    onBackClick: () -> Unit,
    viewModel: PlayerViewModel = viewModel()
) {
    LaunchedEffect(playerName) { viewModel.loadPlayer(playerName) }

    val player by viewModel.player.collectAsState()
    val team by viewModel.team.collectAsState()

    val primaryColor = team?.corPrimaria?.toComposeColor() ?: Color(0xFF1A237E)
    val secondaryColor = team?.corSecundaria?.toComposeColor() ?: Color(0xFF0A0E27)

    // A etiqueta da camisa continua precisando checar a luminância pois o fundo dela é a cor primária
    val corTextoCamisa = if (primaryColor.luminance() > 0.5f) Color(0xFF0A0E27) else Color.White

    var isFavorite by remember { mutableStateOf(false) }

    player?.let { p ->
        // Degradê Dourado Sofisticado para o Card da Estrela
        val fundoDaFigurinha = if (p.estrela) {
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFFD4AF37), // Dourado clássico
                    Color(0xFFFFDF73), // Brilho suave
                    Color(0xFFDAA520)  // Ouro velho
                )
            )
        } else {
            SolidColor(Color.White)
        }

        // Box principal que segura o fundo e o esmaecimento
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(secondaryColor) // Cor base do time
        ) {
            // CAMADA DE ESMAECIMENTO (Overlay escuro)
            // Se for estrela, escurecemos um pouco mais para o brilho dourado destacar
            val alphaOverlay = if (p.estrela) 0.75f else 0.65f
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = alphaOverlay))
            )

            // Brilho/Aura dourada no fundo APENAS para jogadores destaque
            if (p.estrela) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color(0x44FFD700), Color.Transparent),
                                radius = 1200f
                            )
                        )
                )
            }

            // O conteúdo da tela vem por cima de todas as camadas de fundo
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                // Barra Superior
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

                // Header: Foto (Carta) e Informações
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // CARD DO JOGADOR
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(210.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(fundoDaFigurinha)
                    ) {
                        Image(
                            painter = painterResource(id = p.fotoResId),
                            contentDescription = "Foto ${p.nome}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // ESTRELA NO CANTO SUPERIOR DIREITO (Apenas se for destaque)
                        if (p.estrela) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Jogador Destaque",
                                tint = Color(0xFFB8860B), // Dourado mais fechado para contrastar com o fundo
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                                    .size(24.dp)
                            )
                        }

                        // Etiqueta da camisa
                        Box(
                            modifier = Modifier
                                .background(
                                    color = primaryColor,
                                    shape = RoundedCornerShape(bottomEnd = 8.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${p.numCamisa}",
                                color = corTextoCamisa,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Column {
                        Text(
                            text = p.nome.uppercase(),
                            color = Color.White, // Graças ao esmaecimento, sempre fica bom!
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 26.sp
                        )
                        team?.let {
                            Text(
                                text = it.nome.uppercase(),
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "${obterBandeiraEmoji(p.pais)} ${p.pais.uppercase()}",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = p.posicao.uppercase(),
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sobre o Jogador
                Text(
                    text = "SOBRE O JOGADOR",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = p.sobre,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Estatísticas
                Text(
                    text = "ESTATÍSTICAS (2025/2026)",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatWhiteCard(modifier = Modifier.weight(1f), label = "PARTIDAS", value = "${p.partidas}")
                    StatWhiteCard(modifier = Modifier.weight(1f), label = "GOLS", value = "${p.gols}")
                    StatWhiteCard(modifier = Modifier.weight(1f), label = "ASSISTÊNCIAS", value = "${p.assistencia}")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Linhas de Informações Inferiores
                InfoWhiteRow(icon = "👕", label = "NÚMERO DA CAMISA", value = "${p.numCamisa}")
                Spacer(modifier = Modifier.height(8.dp))
                InfoWhiteRow(icon = obterBandeiraEmoji(p.pais), label = "PAÍS", value = p.pais)
                Spacer(modifier = Modifier.height(8.dp))

                val escudoDoTime = painterResource(id = FotoMap.timeFoto(team?.nome ?: ""))
                InfoWhiteRowImage(painter = escudoDoTime, label = "EQUIPE", value = team?.nome ?: "")
            }
        }
    } ?: run {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}

// ─── FUNÇÕES AUXILIARES ─────────────────────────────────────────────────────

fun obterBandeiraEmoji(pais: String): String {
    return when (pais.lowercase().trim()) {
        "brasil" -> "🇧🇷"
        "espanha" -> "🇪🇸"
        "frança", "franca" -> "🇫🇷"
        "alemanha" -> "🇩🇪"
        "portugal" -> "🇵🇹"
        "inglaterra" -> "🏴󠁧󠁢󠁥󠁮󠁧󠁿"
        "itália", "italia" -> "🇮🇹"
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

@Composable
fun StatWhiteCard(modifier: Modifier = Modifier, label: String, value: String) {
    Card(
        modifier = modifier.aspectRatio(0.9f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                color = Color(0xFF0A0E27),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = Color(0xFF0A0E27),
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun InfoWhiteRow(icon: String, label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = icon, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = label, color = Color(0xFF0A0E27), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
            Text(text = value, color = Color(0xFF0A0E27), fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun InfoWhiteRowImage(painter: Painter, label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painter,
                    contentDescription = "Escudo",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = label, color = Color(0xFF0A0E27), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
            Text(text = value, color = Color(0xFF0A0E27), fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}