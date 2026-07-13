
// ─────────────────────────────────────────────────────────────────────────────
// data/repository/TeamRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository

import com.example.albumchampions.data.mock.MockDataSource
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TeamRepository(private val apiService: ApiService) {
        fun getAllTeams(): Flow<List<Team>> = flow {
            emit(MockDataSource.teams)
        }

    fun getTeamById(id: Int): Flow<Team?> = flow {
        emit(MockDataSource.getTeamById(id))
    }
}
