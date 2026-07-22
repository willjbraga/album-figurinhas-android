package com.example.albumchampions.data.remote

import com.example.albumchampions.data.model.*
import com.example.albumchampions.data.remote.dto.*

fun TeamDto.toTeam() = Team(
    id = id, nome = nome, sigla = sigla,
    corPrimaria = corPrimaria, corSecundaria = corSecundaria,
    numVitoria = numVitoria, anoUltimaVitoria = anoUltimaVitoria,
    historia = historia, pais = pais, idioma = idioma,
    anoCriacao = anoCriacao, curiosidade = curiosidade,
    escudoResId = FotoMap.timeFoto(nome)
)

fun PlayerDto.toPlayer() = Player(
    nome = nome,
    estrela = estrela,
    pais = pais,
    numCamisa = numCamisa,
    partidas = partidas,
    gols = gols,
    assistencia = assistencia,
    sobre = sobre,
    idTime = idTime,
    fotoResId = FotoMap.jogadorFoto(nome),
    posicao = "ATACANTE"
)

fun CoachDto.toCoach() = Coach(
    nome = nome,
    perfil = perfil,
    pais = pais,
    idTime = idTime,
    fotoResId = FotoMap.treinadorFoto(nome),
    idioma = "Alemão"
)

fun CompetitionDto.toCompetition() = Competition(
    nome = nome, anoCompeticao = anoCompeticao,
    paisSede = paisSede, cidadeSede = cidadeSede, estadio = estadio
)