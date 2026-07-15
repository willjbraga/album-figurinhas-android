package com.example.albumchampions.data.remote

import com.example.albumchampions.R

object FotoMap {
    private val times = mapOf(
     //   "Real Madrid" to R.drawable.escudo_real_madrid,
        "Barcelona" to R.drawable.escudo_barcelona_teste,   //altera depois
     //   "PSG" to R.drawable.escudo_psg,
     //   "Milan" to R.drawable.escudo_milan,
     //   "Bayern de Munique" to R.drawable.escudo_bayern
    )

    private val jogadores = mapOf(
        "Vinicius Junior" to R.drawable.jogador_vini_teste  //altera depois
        // completar manualmente com os outros 24 jogadores
    )

    private val treinadores = mapOf(
        "Jose Mourinho" to R.drawable.treinador_vini_teste // altera depois
        // completar manualmente com os outros 4 treinadores
    )

    fun timeFoto(nome: String) = times[nome] ?: R.drawable.escudo_barcelona_teste
    fun jogadorFoto(nome: String) = jogadores[nome] ?: R.drawable.jogador_vini_teste
    fun treinadorFoto(nome: String) = treinadores[nome] ?: R.drawable.treinador_vini_teste
}