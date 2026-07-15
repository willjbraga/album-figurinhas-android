// ─────────────────────────────────────────────────────────────────────────────
// viewmodel/PlayerViewModel.kt
// ─────────────────────────────────────────────────────────────────────────────
package com.example.albumchampions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumchampions.data.model.Player
import com.example.albumchampions.data.model.Team
import com.example.albumchampions.data.repository.PlayerRepository
import com.example.albumchampions.data.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val playerRepository: PlayerRepository = PlayerRepository(),
    private val teamRepository: TeamRepository = TeamRepository()
) : ViewModel() {

    private val _player = MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player

    private val _team = MutableStateFlow<Team?>(null)
    val team: StateFlow<Team?> = _team

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadPlayer(playerName: String) {
        _error.value = null
        _isLoading.value = true

        viewModelScope.launch {
            try {
                playerRepository.getPlayerByName(playerName).collect { p ->
                    _player.value = p
                    if (p != null) {
                        try {
                            teamRepository.getTeamById(p.idTime).collect { t ->
                                _team.value = t
                            }
                        } catch (e: Exception) {
                            _error.value = "Erro ao carregar time do jogador: ${e.message}"
                        }
                    }
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar jogador: ${e.message}"
                _isLoading.value = false
            }
        }
    }
}