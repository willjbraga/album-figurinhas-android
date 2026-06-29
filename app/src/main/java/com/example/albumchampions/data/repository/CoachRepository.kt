
// ─────────────────────────────────────────────────────────────────────────────
// data/repository/CoachRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository

import com.example.albumchampions.data.mock.MockDataSource
import com.example.albumchampions.data.model.Coach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoachRepository {
    fun getCoachByTeam(idTime: Int): Flow<Coach?> = flow {
        emit(MockDataSource.getCoachByTeam(idTime))
    }
}