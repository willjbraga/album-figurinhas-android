
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

    fun loadPlayer(playerName: String) {
        viewModelScope.launch {
            playerRepository.getPlayerByName(playerName).collect { p ->
                _player.value = p
                p?.let {
                    teamRepository.getTeamById(it.idTime).collect { t ->
                        _team.value = t
                    }
                }
            }
        }
    }
}
