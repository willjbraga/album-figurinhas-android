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

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadData()
    }

    fun loadData() {
        _error.value = null
        _isLoading.value = true

        viewModelScope.launch {
            try {
                competitionRepository.getCompetition().collect { _competition.value = it }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar competição: ${e.message}"
            }
        }
        viewModelScope.launch {
            try {
                teamRepository.getAllTeams().collect {
                    _teams.value = it
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar times: ${e.message}"
                _isLoading.value = false
            }
        }
    }
}