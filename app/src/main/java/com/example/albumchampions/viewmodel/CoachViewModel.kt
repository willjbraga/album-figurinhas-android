
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

    fun loadCoach(teamId: Int) {
        viewModelScope.launch {
            coachRepository.getCoachByTeam(teamId).collect { c ->
                _coach.value = c
                c?.let {
                    teamRepository.getTeamById(it.idTime).collect { t ->
                        _team.value = t
                    }
                }
            }
        }
    }
}