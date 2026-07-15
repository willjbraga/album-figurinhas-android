
// ─────────────────────────────────────────────────────────────────────────────
// data/repository/TeamRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository


import com.example.albumchampions.data.model.Team
import com.example.albumchampions.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.albumchampions.data.remote.RetrofitClient
import com.example.albumchampions.data.remote.toTeam

/*class TeamRepository(private val apiService: ApiService) {
        fun getAllTeams(): Flow<List<Team>> = flow {
            emit(MockDataSource.teams)
        }

    fun getTeamById(id: Int): Flow<Team?> = flow {
        emit(MockDataSource.getTeamById(id))
    }
}  */

class TeamRepository(private val apiService: ApiService = RetrofitClient.apiService) {

    fun getAllTeams(): Flow<List<Team>> = flow {
        emit(apiService.getTeams().map { it.toTeam() })
    }

    fun getTeamById(id: Int): Flow<Team?> = flow {
        emit(apiService.getTeamById("eq.$id").map { it.toTeam() }.firstOrNull())
    }
}