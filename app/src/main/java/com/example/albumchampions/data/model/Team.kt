// ─────────────────────────────────────────────
// data/model/Team.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.model

data class Team(
    val id: Int,
    val nome: String,
    val sigla: String,
    val corPrimaria: String,      // hex, ex: "#003087" – usado só para tema de fundo
    val corSecundaria: String,    // hex – usado só para tema de fundo
    val numVitoria: Int,
    val anoUltimaVitoria: String,
    val historia: String,
    val pais: String,
    val idioma: String,
    val anoCriacao: Int,
    val curiosidade: String,
    val escudoResId: Int          // R.drawable.escudo_real_madrid, etc.
)
