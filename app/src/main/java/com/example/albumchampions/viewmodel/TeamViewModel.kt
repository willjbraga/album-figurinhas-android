// ─────────────────────────────────────────────────────────────────────────────
// viewmodel/TeamViewModel.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumchampions.data.model.Coach
import com.example.albumchampions.data.model.Player
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.data.repository.CoachRepository
import com.example.albumchampions.data.repository.PlayerRepository
import com.example.albumchampions.data.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamViewModel(
    private val teamRepository: TeamRepository = TeamRepository(),
    private val playerRepository: PlayerRepository = PlayerRepository(),
    private val coachRepository: CoachRepository = CoachRepository()
) : ViewModel() {

    private val _team = MutableStateFlow<Team?>(null)
    val team: StateFlow<Team?> = _team

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    private val _starPlayer = MutableStateFlow<Player?>(null)
    val starPlayer: StateFlow<Player?> = _starPlayer

    private val _coach = MutableStateFlow<Coach?>(null)
    val coach: StateFlow<Coach?> = _coach

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadTeam(teamId: Int) {
        _error.value = null
        _isLoading.value = true

        viewModelScope.launch {
            try {
                teamRepository.getTeamById(teamId).collect { _team.value = it }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar time: ${e.message}"
            }
        }
        viewModelScope.launch {
            try {
                playerRepository.getPlayersByTeam(teamId).collect { list ->
                    _players.value = list
                    _starPlayer.value = list.find { it.estrela }
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar jogadores: ${e.message}"
                _isLoading.value = false
            }
        }
        viewModelScope.launch {
            try {
                coachRepository.getCoachByTeam(teamId).collect { _coach.value = it }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar treinador: ${e.message}"
            }
        }
    }
}