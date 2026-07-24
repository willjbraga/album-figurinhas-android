// ui/screens/HomeScreen.kt
package com.example.albumchampions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.R
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.viewmodel.CompetitionViewModel

val FrauncesFont = FontFamily(Font(R.font.fraunces_72pt_semibold))
val FrauncesFontL = FontFamily(Font(R.font.fraunces_72pt_light))
val FrauncesFontR = FontFamily(Font(R.font.fraunces_72pt_regular))

@Composable
fun HomeScreen(
    onTeamClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit = {},
    viewModel: CompetitionViewModel = viewModel()
) {
    val competition by viewModel.competition.collectAsState()
    val teams by viewModel.teams.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF03071E))
            .systemBarsPadding(),
        contentPadding = PaddingValues(bottom = 32.dp, start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(span = { GridItemSpan(3) }) {
            Box(modifier = Modifier.fillMaxWidth()) {

                // Botão de Favoritos (alinhado proporcionalmente ao novo topo)
                IconButton(
                    onClick = onFavoritesClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Ver Favoritos",
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Cabeçalho com espaçamento limpo e equilibrado
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp), // Reduzido o topo exagerado para dar respiro correto
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "ÁLBUM DE FIGURINHAS",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 16.sp,
                        fontFamily = FrauncesFont,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 2.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "CHAMPIONS\nLEAGUE",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = FrauncesFont,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp,
                        lineHeight = 28.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = competition?.anoCompeticao ?: "2025/2026",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 25.sp,
                        fontFamily = FrauncesFont,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.trofeu),
                        contentDescription = "Troféu UEFA Champions League",
                        modifier = Modifier
                            .size(280.dp)
                            .offset(x = 10.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "EQUIPES PARTICIPANTES",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FrauncesFontR,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.5.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (isLoading) {
            item(span = { GridItemSpan(3) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        } else {
            items(teams) { team ->
                TeamGridItem(team = team, onClick = { onTeamClick(team.id) })
            }
        }
    }
}

@Composable
fun TeamGridItem(team: Team, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = team.nome.uppercase(),
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = FrauncesFontL,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 2,
            minLines = 2,
            lineHeight = 13.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = team.escudoResId),
            contentDescription = "Escudo ${team.nome}",
            modifier = Modifier.size(68.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${team.numVitoria} TÍTULOS",
            color = Color.White.copy(alpha = 0.8f),
            fontFamily = FrauncesFont,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}