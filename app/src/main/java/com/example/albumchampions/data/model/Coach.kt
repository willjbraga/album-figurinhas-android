// ─────────────────────────────────────────────
// data/model/Coach.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.model

data class Coach(
    val nome: String,
    val perfil: String,
    val pais: String,
    val idTime: Int,
    val fotoResId: Int,
    val idioma: String
)