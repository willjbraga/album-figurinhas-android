// ═════════════════════════════════════════════════════════════════════════════
// ui/components/StarPlayerCard.kt
// Card especial para o jogador estrela no álbum do time
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.albumchampions.data.model.Player

@Composable
fun StarPlayerCard(
    player: Player,
    teamColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() }
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(listOf(Color(0xFFFFD700), Color(0xFFFFA000))),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = teamColor.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto com borda dourada
            Box {
                Image(
                    painter = painterResource(id = player.fotoResId),
                    contentDescription = player.nome,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xFFFFD700), CircleShape),
                    contentScale = ContentScale.Crop
                )
                // Ícone estrela sobreposto
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Destaque",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.BottomEnd)
                        .background(Color(0xFF1A237E), CircleShape)
                        .padding(3.dp)
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(player.nome, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text("Nº ${player.numCamisa}  •  ${player.pais}", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
                Spacer(Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    MiniStat("⚽", "${player.gols}")
                    MiniStat("🅰", "${player.assistencia}")
                    MiniStat("🏟", "${player.partidas}")
                }
            }
        }
    }
}

@Composable
private fun MiniStat(icon: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(icon, fontSize = 13.sp)
        Spacer(Modifier.width(2.dp))
        Text(value, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}

