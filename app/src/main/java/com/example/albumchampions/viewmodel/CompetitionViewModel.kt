// ─────────────────────────────────────────────────────────────────────────────
// viewmodel/CompetitionViewModel.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumchampions.data.model.Competition
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.data.repository.CompetitionRepository
import com.example.albumchampions.data.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompetitionViewModel(
    private val competitionRepository: CompetitionRepository = CompetitionRepository(),
    private val teamRepository: TeamRepository = TeamRepository()
) : ViewModel() {

    private val _competition = MutableStateFlow<Competition?>(null)
    val competition: StateFlow<Competition?> = _competition

    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            competitionRepository.getCompetition().collect { _competition.value = it }
        }
        viewModelScope.launch {
            teamRepository.getAllTeams().collect {
                _teams.value = it
                _isLoading.value = false
            }
        }
    }
}



