
// ─────────────────────────────────────────────────────────────────────────────
// data/repository/PlayerRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository

import com.example.albumchampions.data.mock.MockDataSource
import com.example.albumchampions.data.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerRepository {
    fun getPlayersByTeam(idTime: Int): Flow<List<Player>> = flow {
        emit(MockDataSource.getPlayersByTeam(idTime))
    }

    fun getPlayerByName(nome: String): Flow<Player?> = flow {
        emit(MockDataSource.players.find { it.nome == nome })
    }
}

