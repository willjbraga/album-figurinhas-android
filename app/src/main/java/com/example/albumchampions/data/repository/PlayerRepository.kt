
// ─────────────────────────────────────────────────────────────────────────────
// data/repository/PlayerRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository

import com.example.albumchampions.data.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.albumchampions.data.remote.ApiService
import com.example.albumchampions.data.remote.RetrofitClient
import com.example.albumchampions.data.remote.toPlayer

/*class PlayerRepository {
    fun getPlayersByTeam(idTime: Int): Flow<List<Player>> = flow {
        emit(MockDataSource.getPlayersByTeam(idTime))
    }

    fun getPlayerByName(nome: String): Flow<Player?> = flow {
        emit(MockDataSource.players.find { it.nome == nome })
    }
} */

class PlayerRepository(private val apiService: ApiService = RetrofitClient.apiService) {

    fun getPlayersByTeam(idTime: Int): Flow<List<Player>> = flow {
        emit(apiService.getPlayersByTime("eq.$idTime").map { it.toPlayer() })
    }

    fun getPlayerByName(nome: String): Flow<Player?> = flow {
        emit(apiService.getPlayers().map { it.toPlayer() }.find { it.nome == nome })
    }
}

