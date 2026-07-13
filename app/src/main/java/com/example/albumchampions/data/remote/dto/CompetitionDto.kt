// ─────────────────────────────────────────────
// data/remote/dto/CompetitionDto.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CompetitionDto(
    // Competition é o único model sem campo de foto.
    // O DTO ainda existe por consistência: se o backend
    // renomear campos ou adicionar dados que o model não
    // precisa expor, este é o lugar para filtrar.

    @SerializedName("nome")           val nome: String,
    @SerializedName("ano_competicao") val anoCompeticao: String,
    @SerializedName("pais_sede")      val paisSede: String,
    @SerializedName("cidade_sede")    val cidadeSede: String,
    @SerializedName("estadio")        val estadio: String
)