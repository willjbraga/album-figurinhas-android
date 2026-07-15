// ─────────────────────────────────────────────────────────────────────────────
// viewmodel/CoachViewModel.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumchampions.data.model.Coach
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.data.repository.CoachRepository
import com.example.albumchampions.data.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoachViewModel(
    private val coachRepository: CoachRepository = CoachRepository(),
    private val teamRepository: TeamRepository = TeamRepository()
) : ViewModel() {

    private val _coach = MutableStateFlow<Coach?>(null)
    val coach: StateFlow<Coach?> = _coach

    private val _team = MutableStateFlow<Team?>(null)
    val team: StateFlow<Team?> = _team

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadCoach(teamId: Int) {
        _error.value = null
        _isLoading.value = true

        viewModelScope.launch {
            try {
                coachRepository.getCoachByTeam(teamId).collect { c ->
                    _coach.value = c
                    if (c != null) {
                        try {
                            teamRepository.getTeamById(c.idTime).collect { t ->
                                _team.value = t
                            }
                        } catch (e: Exception) {
                            _error.value = "Erro ao carregar time do treinador: ${e.message}"
                        }
                    }
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar treinador: ${e.message}"
                _isLoading.value = false
            }
        }
    }
}