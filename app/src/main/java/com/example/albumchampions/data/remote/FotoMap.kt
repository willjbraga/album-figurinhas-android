package com.example.albumchampions.data.remote

import com.example.albumchampions.R

object FotoMap {
    private val times = mapOf(
        "Real Madrid" to R.drawable.escudo_real_madrid,
        "Barcelona" to R.drawable.escudo_barcelona,
        "Paris Saint-Germain (PSG)" to R.drawable.escudo_psg,
        "PSG" to R.drawable.escudo_psg,
        "Milan" to R.drawable.escudo_milan,
        "Bayern de Munique" to R.drawable.escudo_bayern
    )

    private val jogadores = mapOf(
        // Real Madrid
        "Vinicius Júnior" to R.drawable.jogador_vinicius,
        "Vinicius Junior" to R.drawable.jogador_vinicius,
        "Kylian Mbappé" to R.drawable.jogador_mbappe,
        "Kylian Mbappe" to R.drawable.jogador_mbappe, // Adicionado sem acento
        "Jude Bellingham" to R.drawable.jogador_bellingham,
        "Federico Valverde" to R.drawable.jogador_valverde,
        "Thibaut Courtois" to R.drawable.jogador_courtois,

        // Barcelona
        "Raphinha" to R.drawable.jogador_raphinha,
        "Lamine Yamal" to R.drawable.jogador_yamal,
        "Robert Lewandowski" to R.drawable.jogador_lewandowski,
        "Pedri" to R.drawable.jogador_pedri,
        "Marc-André ter Stegen" to R.drawable.jogador_stegen,
        "Marc-Andre ter Stegen" to R.drawable.jogador_stegen, // Adicionado sem acento
        "Marc Andre ter Stegen" to R.drawable.jogador_stegen, // Adicionado sem hífen

        // PSG
        "Ousmane Dembélé" to R.drawable.jogador_dembele,
        "Ousmane Dembele" to R.drawable.jogador_dembele, // Adicionado sem acento
        "Khvicha Kvaratskhelia" to R.drawable.jogador_kvaratskhelia,
        "Vitinha" to R.drawable.jogador_vitinha,
        "Marquinhos" to R.drawable.jogador_marquinhos,
        "Matvei Safonov" to R.drawable.jogador_safonov,
        "Matvey Safonov" to R.drawable.jogador_safonov, // Adicionado com 'y'

        // Milan
        "Rafael Leão" to R.drawable.jogador_leao,
        "Rafael Leao" to R.drawable.jogador_leao, // Adicionado sem acento
        "Christian Pulisic" to R.drawable.jogador_pulisic,
        "Luka Modric" to R.drawable.jogador_modric,
        "Mike Maignan" to R.drawable.jogador_maignan,
        "Adrien Rabiot" to R.drawable.jogador_rabiot,

        // Bayern
        "Harry Kane" to R.drawable.jogador_kane,
        "Luis Díaz" to R.drawable.jogador_luisdiaz,
        "Luis Diaz" to R.drawable.jogador_luisdiaz, // Adicionado sem acento
        "Michael Olise" to R.drawable.jogador_olise,
        "Joshua Kimmich" to R.drawable.jogador_kimmich,
        "Manuel Neuer" to R.drawable.jogador_neuer
    )

    private val treinadores = mapOf(
        "José Mourinho" to R.drawable.treinador_mourinho,
        "Jose Mourinho" to R.drawable.treinador_mourinho,
        "Hansi Flick" to R.drawable.treinador_flick,
        "Luis Enrique" to R.drawable.treinador_luisenrique,
        "Ruben Amorim" to R.drawable.treinador_amorim,
        "Rúben Amorim" to R.drawable.treinador_amorim,
        "Vincent Kompany" to R.drawable.treinador_kompany
    )

    // Fallbacks para caso falte alguma imagem ou o nome venha diferente do banco
    fun timeFoto(nome: String) = times[nome] ?: R.drawable.escudo_real_madrid
    fun jogadorFoto(nome: String) = jogadores[nome] ?: R.drawable.jogador_vinicius
    fun treinadorFoto(nome: String) = treinadores[nome] ?: R.drawable.treinador_mourinho
}