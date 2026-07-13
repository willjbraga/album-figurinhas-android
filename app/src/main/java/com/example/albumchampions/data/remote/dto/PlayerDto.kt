// ─────────────────────────────────────────────
// data/remote/dto/PlayerDto.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlayerDto(
    @SerializedName("nome")        val nome: String,
    @SerializedName("estrela")     val estrela: Boolean,
    @SerializedName("pais")        val pais: String,
    @SerializedName("num_camisa")  val numCamisa: Int,
    @SerializedName("partidas")    val partidas: Int,
    @SerializedName("gols")        val gols: Int,
    @SerializedName("assistencia") val assistencia: Int,
    @SerializedName("sobre")       val sobre: String,
    @SerializedName("id_time")     val idTime: Int

    // fotoResId NÃO existe aqui — será resolvido no PlayerRepository
    // usando o nome do jogador para encontrar o drawable correto.
)