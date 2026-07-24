// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/CoachScreen.kt
// Tela de detalhes do treinador
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import android.content.Context
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.data.remote.FotoMap
import com.example.albumchampions.viewmodel.CoachViewModel
import kotlinx.coroutines.delay
import androidx.core.content.edit

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

    // ─── MEMÓRIA LOCAL PARA FAVORITOS (SharedPreferences) ────────────────────
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("AlbumPrefs", Context.MODE_PRIVATE) }
    var isFavorite by remember {
        mutableStateOf(sharedPreferences.getBoolean("fav_coach_$teamId", false))
    }

    // ─── ESTADOS DE ANIMAÇÃO DE ENTRADA ──────────────────────────────────────
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(coach, team) {
        if (coach != null && team != null) {
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

    coach?.let { c ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(secondaryColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.65f))
            )

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
                                // Salva a escolha na memória do celular
                                sharedPreferences.edit {
                                    putBoolean(
                                        "fav_coach_$teamId",
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
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(210.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(primaryColor)
                            .background(Color.Black.copy(alpha = 0.25f))
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
                        Text(text = c.nome.uppercase(), color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 26.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        team?.let {
                            Text(text = it.nome.uppercase(), color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "${obterBandeiraEmoji(c.pais)} ${c.pais.uppercase()}", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "PERFIL", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = c.perfil.replace("\n", " ").replace("  ", " "),
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                InfoWhiteRow(icon = obterBandeiraEmoji(c.pais), label = "PAÍS", value = c.pais)
                Spacer(modifier = Modifier.height(8.dp))

                val escudoDoTime = painterResource(id = FotoMap.timeFoto(team?.nome ?: ""))
                InfoWhiteRowImage(painter = escudoDoTime, label = "TIME ATUAL", value = team?.nome ?: "")

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    } ?: run {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}