// ─────────────────────────────────────────────────────────────────────────────
// data/repository/CompetitionRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository

import com.example.albumchampions.data.model.Competition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.albumchampions.data.remote.ApiService
import com.example.albumchampions.data.remote.RetrofitClient
import com.example.albumchampions.data.remote.toCompetition

/* class CompetitionRepository {
    fun getCompetition(): Flow<Competition> = flow {
        // Futuramente substituir por chamada de API/banco remoto
        emit(MockDataSource.competition)
    }
}  */

class CompetitionRepository(private val apiService: ApiService = RetrofitClient.apiService) {

    fun getCompetition(): Flow<Competition> = flow {
        emit(apiService.getCompetitions().map { it.toCompetition() }.first())
    }
}
