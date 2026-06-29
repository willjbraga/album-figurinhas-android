
// ═════════════════════════════════════════════════════════════════════════════
// ui/components/PlayerCard.kt
// Card padrão para jogadores no álbum
// ═════════════════════════════════════════════════════════════════════════════
package com.example.albumchampions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.albumchampions.data.model.Player

@Composable
fun PlayerCard(player: Player, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C2151))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto
            Image(
                painter = painterResource(id = player.fotoResId),
                contentDescription = player.nome,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            // Info principal
            Column(modifier = Modifier.weight(1f)) {
                Text(player.nome, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text("${player.pais}  •  Nº ${player.numCamisa}", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
            }
            // Gols
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("${player.gols}", color = Color(0xFFFFD700), fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text("gols", color = Color.White.copy(alpha = 0.5f), fontSize = 10.sp)
            }
        }
    }
}

