// ─────────────────────────────────────────────────────────────────────────────
// data/repository/CompetitionRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository

import com.example.albumchampions.data.mock.MockDataSource
import com.example.albumchampions.data.model.Competition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CompetitionRepository {
    fun getCompetition(): Flow<Competition> = flow {
        // Futuramente substituir por chamada de API/banco remoto
        emit(MockDataSource.competition)
    }
}


