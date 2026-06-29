
// ═════════════════════════════════════════════════════════════════════════════
// ui/components/CoachCard.kt
// Card do treinador no álbum do time
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
import com.example.albumchampions.data.model.Coach

@Composable
fun CoachCard(coach: Coach, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF252A5E))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = coach.fotoResId),
                contentDescription = coach.nome,
               modifier = Modifier
                    .size(56.dp)
                   .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(14.dp))
            Column {
                Text(coach.nome, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(coach.pais, color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
            }
        }
    }
}