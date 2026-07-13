// ─────────────────────────────────────────────
// data/remote/dto/TeamDto.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TeamDto(
    // @SerializedName mapeia o nome do campo no JSON da API
    // para o nome em camelCase usado no Kotlin.
    // Ex: o banco envia "cor_primaria", o Kotlin chama de corPrimaria.

    @SerializedName("id")                  val id: Int,
    @SerializedName("nome")                val nome: String,
    @SerializedName("sigla")               val sigla: String,
    @SerializedName("cor_primaria")        val corPrimaria: String,
    @SerializedName("cor_secundaria")      val corSecundaria: String,
    @SerializedName("num_vitoria")         val numVitoria: Int,
    @SerializedName("ano_ultima_vitoria")  val anoUltimaVitoria: String,
    @SerializedName("historia")            val historia: String,
    @SerializedName("pais")               val pais: String,
    @SerializedName("idioma")             val idioma: String,
    @SerializedName("ano_criacao")        val anoCriacao: Int,
    @SerializedName("curiosidade")        val curiosidade: String

    // escudoResId NÃO existe aqui — ele é um R.drawable e
    // será resolvido manualmente no TeamRepository.
)
