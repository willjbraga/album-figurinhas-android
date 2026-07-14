// ─────────────────────────────────────────────
// data/remote/dto/CompetitionDto.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CompetitionDto(

    @SerializedName("nome")           val nome: String,
    @SerializedName("ano_competicao") val anoCompeticao: String,
    @SerializedName("pais_sede")      val paisSede: String,
    @SerializedName("cidade_sede")    val cidadeSede: String,
    @SerializedName("estadio")        val estadio: String
)