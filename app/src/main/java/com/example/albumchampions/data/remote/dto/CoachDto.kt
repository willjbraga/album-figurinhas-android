// ─────────────────────────────────────────────
// data/remote/dto/CoachDto.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoachDto(
    @SerializedName("nome")    val nome: String,
    @SerializedName("perfil")  val perfil: String,
    @SerializedName("pais")    val pais: String,
    @SerializedName("id_time") val idTime: Int

    // fotoResId NÃO existe aqui — será resolvido no CoachRepository
    // usando o nome do treinador para encontrar o drawable correto.
)