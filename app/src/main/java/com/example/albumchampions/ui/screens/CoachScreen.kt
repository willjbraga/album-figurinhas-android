// ═════════════════════════════════════════════════════════════════════════════
// ui/screens/CoachScreen.kt
// Tela de detalhes do treinador
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.albumchampions.viewmodel.CoachViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoachScreen(
    teamId: Int,
    onBackClick: () -> Unit,
    viewModel: CoachViewModel = viewModel()
) {
    LaunchedEffect(teamId) { viewModel.loadCoach(teamId) }

    val coach by viewModel.coach.collectAsState()
    val team  by viewModel.team.collectAsState()

    val primaryColor   = team?.corPrimaria?.toComposeColor()   ?: Color(0xFF1A237E)
    val secondaryColor = team?.corSecundaria?.toComposeColor() ?: Color(0xFF0A0E27)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Treinador", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryColor)
            )
        }
    ) { padding ->
        coach?.let { c ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(secondaryColor)
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto
                Image(
                    painter = painterResource(id = c.fotoResId),
                    contentDescription = "Foto ${c.nome}",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))

                // Nome
                Text(c.nome, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))

                // País
                Text(c.pais, color = Color.White.copy(alpha = 0.65f), fontSize = 15.sp)
                Spacer(Modifier.height(4.dp))

                // Time atual
                team?.let {
                    Text(
                        text = it.nome,
                        color = Color(0xFFFFD700),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(24.dp))

                // Perfil
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = primaryColor.copy(alpha = 0.2f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Perfil", color = Color(0xFFFFD700), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(c.perfil, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp, lineHeight = 22.sp)
                    }
                }
            }
        } ?: run {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

