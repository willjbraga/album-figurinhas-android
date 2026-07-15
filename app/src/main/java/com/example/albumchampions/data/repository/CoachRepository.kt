
// ─────────────────────────────────────────────────────────────────────────────
// data/repository/CoachRepository.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.data.repository

import com.example.albumchampions.data.model.Coach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.albumchampions.data.remote.ApiService
import com.example.albumchampions.data.remote.RetrofitClient
import com.example.albumchampions.data.remote.toCoach

/*class CoachRepository {
    fun getCoachByTeam(idTime: Int): Flow<Coach?> = flow {
        emit(MockDataSource.getCoachByTeam(idTime))
    }
} */

class CoachRepository(private val apiService: ApiService = RetrofitClient.apiService) {

    fun getCoachByTeam(idTime: Int): Flow<Coach?> = flow {
        emit(apiService.getCoachByTime("eq.$idTime").map { it.toCoach() }.firstOrNull())
    }
}

