// ─────────────────────────────────────────────
// data/remote/ApiService.kt
// ─────────────────────────────────────────────
package com.example.albumchampions.data.remote

import com.example.albumchampions.data.remote.dto.CoachDto
import com.example.albumchampions.data.remote.dto.CompetitionDto
import com.example.albumchampions.data.remote.dto.PlayerDto
import com.example.albumchampions.data.remote.dto.TeamDto
import retrofit2.http.GET
import retrofit2.http.Query

// O Supabase REST API filtra registros via query params no padrão:
//   ?campo=eq.<valor>      → WHERE campo = valor
//   ?campo=eq.true         → WHERE campo = true
// Não usa @Path como uma API convencional.

interface ApiService {

    // ── Times ─────────────────────────────────────────────────────────────────

    // GET /rest/v1/times
    @GET("times")
    suspend fun getTeams(): List<TeamDto>

    // GET /rest/v1/times?id=eq.1
    @GET("times")
    suspend fun getTeamById(
        @Query("id") id: String  // ex: "eq.1"
    ): List<TeamDto>             // Supabase sempre retorna lista

    // ── Treinadores ───────────────────────────────────────────────────────────

    // GET /rest/v1/treinador
    @GET("treinador")
    suspend fun getCoaches(): List<CoachDto>

    // GET /rest/v1/treinador?id_time=eq.1
    @GET("treinador")
    suspend fun getCoachByTime(
        @Query("id_time") idTime: String  // ex: "eq.1"
    ): List<CoachDto>

    // ── Jogadores ─────────────────────────────────────────────────────────────

    // GET /rest/v1/jogadores
    @GET("jogadores")
    suspend fun getPlayers(): List<PlayerDto>

    // GET /rest/v1/jogadores?id_time=eq.1
    @GET("jogadores")
    suspend fun getPlayersByTime(
        @Query("id_time") idTime: String  // ex: "eq.1"
    ): List<PlayerDto>

    // GET /rest/v1/jogadores?estrela=eq.true
    @GET("jogadores")
    suspend fun getEstrelas(
        @Query("estrela") estrela: String = "eq.true"
    ): List<PlayerDto>

    // ── Competições ───────────────────────────────────────────────────────────

    // GET /rest/v1/competicao
    @GET("competicao")
    suspend fun getCompetitions(): List<CompetitionDto>

    // GET /rest/v1/competicao?nome=eq.Champions League
    @GET("competicao")
    suspend fun getCompetitionByNome(
        @Query("nome") nome: String  // ex: "eq.Champions League"
    ): List<CompetitionDto>
}