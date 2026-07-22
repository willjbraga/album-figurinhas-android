
// ─────────────────────────────────────────────
// data/model/Player.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.model

data class Player(
    val nome: String,
    val estrela: Boolean,         // true = card destaque no álbum
    val pais: String,
    val numCamisa: Int,
    val partidas: Int,
    val gols: Int,
    val assistencia: Int,
    val sobre: String,
    val idTime: Int,
    val fotoResId: Int,            // R.drawable.jogador_vinicius, etc.
    val posicao: String
)