// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/TeamDetailScreen.kt
// Detalhes completos do time (layout adaptado conforme o mockup)
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.viewmodel.TeamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(
    teamId: Int,
    onBackClick: () -> Unit,
    viewModel: TeamViewModel = viewModel()
) {
    LaunchedEffect(teamId) { viewModel.loadTeam(teamId) }

    val team by viewModel.team.collectAsState()
    var isFavorite by remember { mutableStateOf(false) }

    // Conversão direta da cor do banco para Color do Compose
    val secondaryColor = when (team?.nome?.lowercase()) {
        "barcelona" -> Color(0xFF3A0018)
        "bayern de munique", "bayern" -> Color(0xFF595959)
        "real madrid" -> Color(0xFF594206)
        "milan" -> Color(0xFF000000)
        "psg" -> Color(0xFF4C0E0A)
        else -> parseHexColor(team?.corSecundaria, default = Color(0xFF03071E))
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
        team?.let { t ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(secondaryColor)
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ── Escudo e Títulos do Time ───────────────────────────────
                Image(
                    painter = painterResource(id = t.escudoResId),
                    contentDescription = "Escudo ${t.nome}",
                    modifier = Modifier.size(130.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = t.nome.uppercase(),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = t.sigla.uppercase(),
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(Modifier.height(24.dp))

                // ── Lista de Informações (Badges em Linhas Brancas) ────────
                InfoRowItem(
                    label = "PAÍS",
                    value = t.pais,
                    emoji = "🇪🇸"
                )
                Spacer(Modifier.height(8.dp))

                InfoRowItem(
                    label = "ANO DE FUNDAÇÃO",
                    value = "${t.anoCriacao}"
                )
                Spacer(Modifier.height(8.dp))

                InfoRowItem(
                    label = "IDIOMAS",
                    value = t.idioma
                )
                Spacer(Modifier.height(8.dp))

                InfoRowItem(
                    label = "Nº DE TÍTULOS NA CHAMPIONS",
                    value = "${t.numVitoria}"
                )
                Spacer(Modifier.height(8.dp))

                InfoRowItem(
                    label = "ÚLTIMO TÍTULO",
                    value = t.anoUltimaVitoria,
                    isStar = true
                )

                Spacer(Modifier.height(28.dp))

                // ── Seção História ─────────────────────────────────────────
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "HISTÓRIA",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = t.historia,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }

                Spacer(Modifier.height(24.dp))

                // ── Seção Curiosidade ──────────────────────────────────────
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "CURIOSIDADE",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = t.curiosidade,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }

                Spacer(Modifier.height(24.dp))
            }
        } ?: run {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}

// ── Componente da Linha de Informações ──────────────────────────────────────
@Composable
private fun InfoRowItem(
    label: String,
    value: String,
    emoji: String? = null,
    isStar: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isStar) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = label,
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (emoji != null) {
                    Text(text = emoji, fontSize = 14.sp)
                    Spacer(Modifier.width(6.dp))
                }
                Text(
                    text = value,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ── Função Auxiliar de Conversão de Cor ─────────────────────────────────────
private fun parseHexColor(hex: String?, default: Color): Color {
    if (hex.isNullOrEmpty()) return default
    return try {
        Color(android.graphics.Color.parseColor(hex))
    } catch (_: Exception) {
        default
    }
}