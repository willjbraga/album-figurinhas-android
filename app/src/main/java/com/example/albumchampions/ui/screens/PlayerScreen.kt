// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/PlayerScreen.kt
// Tela de detalhes do jogador
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import android.content.Context
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.data.remote.FotoMap
import com.example.albumchampions.viewmodel.PlayerViewModel
import kotlinx.coroutines.delay
import androidx.core.content.edit
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.albumchampions.R

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
    val corTextoCamisa = if (primaryColor.luminance() > 0.5f) Color(0xFF0A0E27) else Color.White

    // ─── MEMÓRIA LOCAL PARA FAVORITOS (SharedPreferences) ────────────────────
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("AlbumPrefs", Context.MODE_PRIVATE) }
    var isFavorite by remember {
        mutableStateOf(sharedPreferences.getBoolean("fav_player_$playerName", false))
    }

    // ─── ESTADOS DE ANIMAÇÃO DE ENTRADA ──────────────────────────────────────
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(player, team) {
        if (player != null && team != null) {
            delay(150)
            startAnimation = true
        }
    }

    val slideOffset by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 50.dp,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "slideAnim"
    )
    val fadeAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "fadeAnim"
    )

    // ─── ANIMAÇÃO DA ESTRELA DE FAVORITO ─────────────────────────────────────
    val starScale by animateFloatAsState(
        targetValue = if (isFavorite) 1.3f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "starAnim"
    )

    // ─── ANIMAÇÃO DA AURA ────────────────────────────────────────────────────
    val infiniteTransition = rememberInfiniteTransition(label = "aura")
    val auraAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "auraAlpha"
    )

    // ─── ESTADO E ANIMAÇÃO DO GIRO DA CARTA ─────────────────────────────────
    var isFlipped by remember { mutableStateOf(false) }
    val flipRotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "flipAnim"
    )

    player?.let { p ->
        val fundoDaFigurinha = if (p.estrela) {
            Brush.linearGradient(
                colors = listOf(Color(0xFFD4AF37), Color(0xFFFFDF73), Color(0xFFDAA520))
            )
        } else {
            SolidColor(Color.White)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(secondaryColor)
        ) {
            val alphaOverlay = if (p.estrela) 0.75f else 0.65f
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = alphaOverlay)))

            if (p.estrela) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color(0xFFFFD700).copy(alpha = auraAlpha), Color.Transparent),
                                radius = 1200f
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .graphicsLayer {
                        alpha = fadeAlpha
                        translationY = slideOffset.toPx()
                    }
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp).clickable { onBackClick() }
                    )

                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Favoritar",
                        tint = if (isFavorite) Color(0xFFFFD700) else Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                isFavorite = !isFavorite
                                // Salva a escolha na memória do celular na hora!
                                sharedPreferences.edit {
                                    putBoolean(
                                        "fav_player_$playerName",
                                        isFavorite
                                    )
                                }
                            }
                            .graphicsLayer {
                                scaleX = starScale
                                scaleY = starScale
                            }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // ─── COMPONENTE DA CARTA COM LÓGICA DE GIRO (FLIP) ───
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(210.dp)
                            .graphicsLayer {
                                rotationY = flipRotation
                                cameraDistance = 12f * density
                            }
                            .clickable { isFlipped = !isFlipped }
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        if (flipRotation <= 90f) {
                            // FRENTE DA CARTA
                            Box(modifier = Modifier.fillMaxSize().background(fundoDaFigurinha)) {
                                Image(
                                    painter = painterResource(id = p.fotoResId),
                                    contentDescription = "Foto ${p.nome}",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )

                                if (p.estrela) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "Jogador Destaque",
                                        tint = Color(0xFFB8860B),
                                        modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).size(24.dp)
                                    )
                                }

                                // ─── NÚMERO DA CAMISA COM BORDAS SUTIS ───
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = primaryColor,
                                            shape = RoundedCornerShape(bottomEnd = 8.dp)
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black.copy(alpha = 0.2f), // A bordinha leve aqui!
                                            shape = RoundedCornerShape(bottomEnd = 8.dp)
                                        )
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(text = "${p.numCamisa}", color = corTextoCamisa, fontWeight = FontWeight.Bold, fontFamily = FrauncesFontR, fontSize = 18.sp)
                                }
                            }
                        } else {
                            // VERSO DA CARTA (COM O TROFÉU)
                            val fundoVerso = if (p.estrela) {
                                Brush.radialGradient(colors = listOf(Color(0xFFDAA520), Color(0xFF8B6508)), radius = 500f)
                            } else {
                                Brush.radialGradient(colors = listOf(Color(0xFF3A4A6A), Color(0xFF0A0E27)), radius = 600f)
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer { rotationY = 180f } // Desfaz o espelhamento
                                    .background(fundoVerso),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    // AQUI ENTRA O TROFÉU!
                                    Image(
                                        painter = painterResource(id = R.drawable.trofeuc),
                                        contentDescription = "Troféu da Champions",
                                        modifier = Modifier.size(120.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "CHAMPIONS",
                                        color = if (p.estrela) Color.White else Color(0xFFD4AF37),
                                        fontSize = 14.sp,
                                        fontFamily = FrauncesFont,
                                        fontWeight = FontWeight.ExtraBold,
                                        letterSpacing = 2.sp
                                    )
                                }
                            }
                        }
                    }
                    // ───────────────────────────────────────────────────────

                    Spacer(modifier = Modifier.width(20.dp))

                    Column {
                        Text(text = p.nome.uppercase(), color = Color.White, fontSize = 22.sp, fontFamily = FrauncesFontR, fontWeight = FontWeight.ExtraBold, lineHeight = 26.sp)
                        team?.let {
                            Text(text = it.nome.uppercase(), color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp, fontFamily = FrauncesFontR, fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "${obterBandeiraEmoji(p.pais)} ${p.pais.uppercase()}", color = Color.White, fontSize = 14.sp, fontFamily = FrauncesFontR, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "SOBRE O JOGADOR", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, fontFamily = FrauncesFont)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = p.sobre, color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp, fontFamily = FrauncesFontL, lineHeight = 20.sp)

                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "ESTATÍSTICAS (2025/2026)", color = Color.White, fontWeight = FontWeight.ExtraBold, fontFamily = FrauncesFont, fontSize = 16.sp)
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
            modifier = Modifier.fillMaxSize().padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, color = Color(0xFF0A0E27), fontSize = 13.sp, fontWeight = FontWeight.Bold, fontFamily = FrauncesFontR, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, color = Color(0xFF0A0E27), fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FrauncesFont)
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = icon, fontSize = 18.sp, fontFamily = FrauncesFontR)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = label, color = Color(0xFF0A0E27), fontSize = 12.sp, fontFamily = FrauncesFontR, fontWeight = FontWeight.SemiBold)
            }
            Text(text = value, color = Color(0xFF0A0E27), fontSize = 14.sp, fontFamily = FrauncesFont, fontWeight = FontWeight.ExtraBold)
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painter, contentDescription = "Escudo", modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = label, color = Color(0xFF0A0E27), fontSize = 12.sp, fontFamily = FrauncesFontR, fontWeight = FontWeight.SemiBold)
            }
            Text(text = value, color = Color(0xFF0A0E27), fontSize = 14.sp, fontFamily = FrauncesFont, fontWeight = FontWeight.ExtraBold)
        }
    }
}