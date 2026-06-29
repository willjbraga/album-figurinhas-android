// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/TeamDetailScreen.kt
// Detalhes completos do time
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

    val primaryColor   = team?.corPrimaria?.toComposeColor()   ?: Color(0xFF1A237E)
    val secondaryColor = team?.corSecundaria?.toComposeColor() ?: Color(0xFF0A0E27)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(team?.nome ?: "Detalhes", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryColor)
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Escudo
                Image(
                    painter = painterResource(id = t.escudoResId),
                    contentDescription = "Escudo ${t.nome}",
                    modifier = Modifier.size(110.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(Modifier.height(16.dp))

                // Badges rápidos
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoBadge(label = "Sigla", value = t.sigla, primary = primaryColor)
                    InfoBadge(label = "Títulos", value = "${t.numVitoria}", primary = primaryColor)
                    InfoBadge(label = "Última conquista", value = t.anoUltimaVitoria, primary = primaryColor)
                }
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoBadge(label = "País", value = t.pais, primary = primaryColor)
                    InfoBadge(label = "Idioma", value = t.idioma, primary = primaryColor)
                    InfoBadge(label = "Fundação", value = "${t.anoCriacao}", primary = primaryColor)
                }
                Spacer(Modifier.height(20.dp))

                // História
                SectionCard(title = "História", body = t.historia, primaryColor = primaryColor)
                Spacer(Modifier.height(12.dp))

                // Curiosidade
                SectionCard(title = "Curiosidade", body = t.curiosidade, primaryColor = primaryColor)
            }
        } ?: run {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun InfoBadge(label: String, value: String, primary: Color) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = primary.copy(alpha = 0.25f)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(label, color = Color.White.copy(alpha = 0.65f), fontSize = 11.sp)
        }
    }
}

@Composable
private fun SectionCard(title: String, body: String, primaryColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = primaryColor.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, color = Color(0xFFFFD700), fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Spacer(Modifier.height(8.dp))
            Text(body, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp, lineHeight = 22.sp)
        }
    }
}
