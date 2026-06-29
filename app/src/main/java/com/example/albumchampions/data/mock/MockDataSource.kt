// data/mock/MockDataSource.kt
package com.example.albumchampions.data.mock

import com.example.albumchampions.R
import com.example.albumchampions.data.model.*

object MockDataSource {

    // ── Competição ──────────────────────────────────────────────────────────
    val competition = Competition(
        nome = "UEFA Champions League",
        anoCompeticao = "2024/2025",
        paisSede = "Alemanha",
        cidadeSede = "Munique",
        estadio = "Allianz Arena"
    )

    // ── Times ───────────────────────────────────────────────────────────────
    val teams = listOf(
        Team(
            id = 1,
            nome = "Real Madrid",
            sigla = "RMA",
            corPrimaria = "#FFFFFF",
            corSecundaria = "#003087",
            numVitoria = 15,
            anoUltimaVitoria = "2024",
            historia = "O Real Madrid é o clube com mais títulos na história da UEFA Champions League. Fundado em 1902, o clube espanhol tornou-se símbolo de excelência no futebol mundial, acumulando conquistas históricas ao longo de mais de um século.",
            pais = "Espanha",
            idioma = "Espanhol",
            anoCriacao = 1902,
            curiosidade = "O Real Madrid foi o primeiro clube a ganhar cinco títulos consecutivos da Copa dos Campeões Europeus, entre 1956 e 1960.",
          //  escudoResId = R.drawable.escudo_real_madrid
            escudoResId = R.drawable.escudo_barcelona_teste        //teste
        ),
        Team(
            id = 2,
            nome = "Barcelona",
            sigla = "BAR",
            corPrimaria = "#A50044",
            corSecundaria = "#004D98",
            numVitoria = 5,
            anoUltimaVitoria = "2015",
            historia = "O FC Barcelona é um dos clubes mais populares do mundo. Fundado em 1899 por Joan Gamper, o Barça é conhecido pelo seu estilo de jogo ofensivo e pela sua famosa academia La Masia, que formou gerações de campeões.",
            pais = "Espanha",
            idioma = "Catalão / Espanhol",
            anoCriacao = 1899,
            curiosidade = "O Barcelona foi o primeiro clube a conquistar o sextuple (seis títulos em um único ano), em 2009, sob o comando de Pep Guardiola.",
         //   escudoResId = R.drawable.escudo_barcelona
            escudoResId = R.drawable.escudo_barcelona_teste        //teste
        ),
        Team(
            id = 3,
            nome = "Paris Saint-Germain",
            sigla = "PSG",
            corPrimaria = "#003087",
            corSecundaria = "#DA291C",
            numVitoria = 0,
            anoUltimaVitoria = "Nunca venceu",
            historia = "O Paris Saint-Germain foi fundado em 1970 e se tornou o maior clube da França. Desde a aquisição pelo fundo Qatar Sports Investments em 2011, o clube investiu pesadamente e montou elencos estrelados para conquistar a Champions League.",
            pais = "França",
            idioma = "Francês",
            anoCriacao = 1970,
            curiosidade = "O PSG chegou à final da Champions League em 2020, perdendo para o Bayern de Munique por 1 a 0.",
         //   escudoResId = R.drawable.escudo_psg
            escudoResId = R.drawable.escudo_barcelona_teste        //teste
        ),
        Team(
            id = 4,
            nome = "Milan",
            sigla = "MIL",
            corPrimaria = "#FB090B",
            corSecundaria = "#000000",
            numVitoria = 7,
            anoUltimaVitoria = "2007",
            historia = "O AC Milan é um dos clubes mais vitoriosos da história da Champions League. Fundado em 1899, o clube italiano viveu sua época de ouro entre as décadas de 1980 e 2000, sob o comando do lendário técnico Arrigo Sacchi.",
            pais = "Itália",
            idioma = "Italiano",
            anoCriacao = 1899,
            curiosidade = "O Milan detém o recorde de permanência no 1º lugar do ranking da UEFA, tendo ficado na liderança por 12 anos consecutivos.",
        //    escudoResId = R.drawable.escudo_milan
            escudoResId = R.drawable.escudo_barcelona_teste        //teste
        ),
        Team(
            id = 5,
            nome = "Bayern de Munique",
            sigla = "BAY",
            corPrimaria = "#DC052D",
            corSecundaria = "#0066B2",
            numVitoria = 6,
            anoUltimaVitoria = "2020",
            historia = "O Bayern de Munique é o clube mais bem-sucedido da Alemanha e um dos maiores da Europa. Fundado em 1900, o clube bávaro é conhecido por sua consistência e por revelar e contratar os melhores talentos do futebol mundial.",
            pais = "Alemanha",
            idioma = "Alemão",
            anoCriacao = 1900,
            curiosidade = "O Bayern é o único clube alemão a ter vencido a Champions League no próprio estádio, em 2012, quando ganhou o Chelsea no Allianz Arena.",
          //  escudoResId = R.drawable.escudo_bayern
            escudoResId = R.drawable.escudo_barcelona_teste        //teste
        )
    )

    // ── Treinadores ─────────────────────────────────────────────────────────
    val coaches = listOf(
        Coach(
            nome = "José Mourinho",
            perfil = "Um dos treinadores mais vencedores da história do futebol. Conhecido pelo seu pragmatismo tático e forte personalidade, Mourinho já conquistou a Champions League com Porto (2004) e Inter de Milão (2010), sendo um dos poucos técnicos a vencer o torneio com diferentes clubes.",
            pais = "Portugal",
            idTime = 1,
         //   fotoResId = R.drawable.treinador_mourinho
            fotoResId = R.drawable.treinador_vini_teste  //teste
        ),
        Coach(
            nome = "Hansi Flick",
            perfil = "Treinador alemão que alcançou notoriedade ao conquistar o sextuplete pelo Bayern de Munique em 2020. Conhecido pelo seu futebol intenso e organizado no pressing alto, Flick assumiu o Barcelona em 2024 com a missão de devolver o clube ao topo do futebol europeu.",
            pais = "Alemanha",
            idTime = 2,
         //   fotoResId = R.drawable.treinador_flick
            fotoResId = R.drawable.treinador_vini_teste  //teste
        ),
        Coach(
            nome = "Luis Enrique",
            perfil = "Ex-jogador e treinador espanhol que conquistou a Champions League como técnico do Barcelona em 2015. No PSG desde 2023, Enrique tenta implementar um estilo de jogo mais coletivo e menos dependente de estrelas individuais.",
            pais = "Espanha",
            idTime = 3,
          //  fotoResId = R.drawable.treinador_luisenrique
            fotoResId = R.drawable.treinador_vini_teste  //teste
        ),
        Coach(
            nome = "Ruben Amorim",
            perfil = "Jovem treinador português que se destacou pelo trabalho no Sporting de Lisboa, onde criou um sistema tático 3-4-3 muito característico. Assumiu o Milan em 2024 com a proposta de trazer um futebol moderno e intenso ao clube rossonero.",
            pais = "Portugal",
            idTime = 4,
         //   fotoResId = R.drawable.treinador_amorim
            fotoResId = R.drawable.treinador_vini_teste  //teste
        ),
        Coach(
            nome = "Vincent Kompany",
            perfil = "Ex-zagueiro belga que teve uma carreira vitoriosa como jogador e iniciou com destaque na carreira de treinador pelo Burnley. No Bayern desde 2024, Kompany busca trazer um futebol mais vertical e agressivo para o clube alemão.",
            pais = "Bélgica",
            idTime = 5,
         //   fotoResId = R.drawable.treinador_kompany
            fotoResId = R.drawable.treinador_vini_teste  //teste
        )
    )

    // ── Jogadores ────────────────────────────────────────────────────────────
    val players = listOf(

        // Real Madrid (idTime = 1)
        Player("Vinícius Júnior", estrela = true,  pais = "Brasil",   numCamisa = 7,  partidas = 34, gols = 24, assistencia = 11, sobre = "Atacante brasileiro explosivo, conhecido pelos dribles desconcertantes e pela velocidade assustadora. Considerado um dos melhores jogadores do mundo, Vinícius foi peça fundamental nos títulos recentes do Real Madrid.",idTime = 1, fotoResId = R.drawable.jogador_vini_teste ), //, fotoResId = R.drawable.jogador_vinicius
        Player("Kylian Mbappé",    estrela = false, pais = "França",   numCamisa = 9,  partidas = 30, gols = 20, assistencia = 7,  sobre = "Um dos maiores talentos da geração atual. Campeão mundial em 2018 com a França, Mbappé chegou ao Real Madrid em 2024 para ser o novo galáctico do clube merengue.", idTime = 1, fotoResId = R.drawable.jogador_vini_teste), //idTime = 1, fotoResId = R.drawable.jogador_vinicius
        Player("Jude Bellingham",  estrela = false, pais = "Inglaterra", numCamisa = 5, partidas = 32, gols = 16, assistencia = 9, sobre = "Meio-campista inglês que chegou ao Real Madrid em 2023 e rapidamente se tornou uma das peças mais importantes do time. Destaca-se pela inteligência tática e capacidade de finalização.", idTime = 1, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_bellingham
        Player("Federico Valverde", estrela = false, pais = "Uruguai", numCamisa = 8, partidas = 35, gols = 8, assistencia = 12, sobre = "Meio-campista uruguaio completo, com enorme capacidade física e técnica. Valverde é o motor do Real Madrid e um dos jogadores mais incansáveis do futebol mundial.", idTime = 1, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_valverde
        Player("Thibaut Courtois", estrela = false, pais = "Bélgica", numCamisa = 1, partidas = 28, gols = 0, assistencia = 0, sobre = "Considerado um dos melhores goleiros do mundo, Courtois foi o herói da final da Champions League 2022, com atuação histórica contra o Liverpool. Sua envergadura e reflexos são excepcionais.", idTime = 1, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_courtois

        // Barcelona (idTime = 2)
        Player("Raphinha",             estrela = true,  pais = "Brasil",   numCamisa = 11, partidas = 33, gols = 19, assistencia = 13, sobre = "Atacante brasileiro versátil que se tornou peça central no novo Barcelona de Hansi Flick. Raphinha combina velocidade, técnica e uma finalização precisa, sendo uma das revelações da temporada.", idTime = 2, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_raphinha
        Player("Lamine Yamal",         estrela = false, pais = "Espanha",  numCamisa = 19, partidas = 31, gols = 14, assistencia = 16, sobre = "Prodígio espanhol que já é considerado um dos maiores talentos da história do futebol. Com apenas 17 anos, Yamal conquistou a Eurocopa 2024 com a Espanha e é a nova joia do Barcelona.", idTime = 2, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_yamal
        Player("Robert Lewandowski",   estrela = false, pais = "Polônia",  numCamisa = 9,  partidas = 30, gols = 22, assistencia = 5,  sobre = "Um dos melhores centroavantes da história do futebol. Lewandowski chegou ao Barcelona em 2022 e continua sendo um artilheiro implacável, mesmo após os 35 anos.", idTime = 2, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_lewandowski
        Player("Pedri",                estrela = false, pais = "Espanha",  numCamisa = 8,  partidas = 26, gols = 7,  assistencia = 10, sobre = "Meio-campista espanhol de técnica refinada, considerado herdeiro do estilo de jogo de Iniesta e Xavi. Pedri é a bússola do meio-campo do Barcelona.", idTime = 2, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_pedri
        Player("Marc-André ter Stegen", estrela = false, pais = "Alemanha", numCamisa = 1, partidas = 25, gols = 0, assistencia = 0, sobre = "Goleiro alemão completo que, além das defesas, contribui ativamente para a saída de bola do Barcelona. Considerado um dos melhores goleiros da sua geração.", idTime = 2, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_terstegen

        // PSG (idTime = 3)
        Player("Ousmane Dembélé",        estrela = true,  pais = "França",   numCamisa = 10, partidas = 32, gols = 18, assistencia = 14, sobre = "Atacante francês de velocidade assustadora e habilidade técnica. Dembélé tornou-se o principal criador de jogadas do PSG e um dos melhores atacantes da Europa na atualidade.", idTime = 3, fotoResId = R.drawable.jogador_vini_teste),  //,fotoResId = R.drawable.jogador_dembele
        Player("Khvicha Kvaratskhelia",  estrela = false, pais = "Geórgia",  numCamisa = 77, partidas = 28, gols = 13, assistencia = 10, sobre = "Revelação da temporada 2022/23 pelo Napoli, onde ajudou o clube a conquistar o scudetto. O georgiano chegou ao PSG em 2025 com sua característica de criar jogadas do nada.", idTime = 3, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_kvaratskhelia
        Player("Vitinha",                estrela = false, pais = "Portugal", numCamisa = 17, partidas = 34, gols = 9,  assistencia = 11, sobre = "Meio-campista português de excelente técnica e leitura de jogo. Vitinha é o maestro do PSG, responsável por organizar o jogo e distribuir a bola com precisão.", idTime = 3, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_vitinha
        Player("Marquinhos",             estrela = false, pais = "Brasil",   numCamisa = 5,  partidas = 33, gols = 4,  assistencia = 2,  sobre = "Capitão e símbolo do PSG, Marquinhos é um dos melhores zagueiros do mundo. O brasileiro lidera a defesa parisiense com experiência e liderança incontestáveis.", idTime = 3, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_marquinhos
        Player("Matvey Safonov",         estrela = false, pais = "Rússia",   numCamisa = 1,  partidas = 22, gols = 0,  assistencia = 0,  sobre = "Goleiro russo que chegou ao PSG em 2024 para ser a nova referência entre as traves do clube francês. Safonov se destaca pelos reflexos rápidos e pelo domínio aéreo.", idTime = 3, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_safonov

        // Milan (idTime = 4)
        Player("Rafael Leão",     estrela = true,  pais = "Portugal", numCamisa = 10, partidas = 32, gols = 17, assistencia = 12, sobre = "Atacante português considerado um dos mais talentosos do futebol mundial. Leão é o principal jogador do Milan e um dos mais imprevisíveis atacantes da Serie A, capaz de mudar qualquer jogo sozinho.", idTime = 4, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_leao
        Player("Christian Pulisic", estrela = false, pais = "EUA",      numCamisa = 11, partidas = 30, gols = 14, assistencia = 9,  sobre = "Atacante americano que se consolidou como referência no Milan. Pulisic é reconhecido pela intensidade, inteligência tática e pela capacidade de atuar em diferentes posições no ataque.", idTime = 4, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_pulisic
        Player("Luka Modrić",     estrela = false, pais = "Croácia",  numCamisa = 10, partidas = 28, gols = 5,  assistencia = 8,  sobre = "Lenda do futebol mundial, Modrić chegou ao Milan em 2025 para uma última grande aventura. O croata ainda impressiona pela visão de jogo e elegância com a bola.", idTime = 4, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_modric
        Player("Mike Maignan",    estrela = false, pais = "França",   numCamisa = 16, partidas = 30, gols = 0,  assistencia = 0,  sobre = "Goleiro francês considerado um dos melhores do mundo. Maignan é peça fundamental no Milan e na seleção francesa, destacando-se pelos reflexos e pela liderança da defesa.", idTime = 4, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_maignan
        Player("Adrien Rabiot",   estrela = false, pais = "França",   numCamisa = 25, partidas = 29, gols = 7,  assistencia = 6,  sobre = "Meio-campista francês de físico avantajado e grande qualidade técnica. Rabiot chegou ao Milan em 2024 e se tornou peça importante na armação das jogadas e na marcação.", idTime = 4, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_rabiot

        // Bayern (idTime = 5)
        Player("Harry Kane",     estrela = true,  pais = "Inglaterra", numCamisa = 9,  partidas = 34, gols = 30, assistencia = 8,  sobre = "Artilheiro implacável e um dos melhores centroavantes da história do futebol inglês. Kane chegou ao Bayern em 2023 e já quebrou recordes de gols no clube alemão.", idTime = 5, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_kane
        Player("Luis Díaz",      estrela = false, pais = "Colômbia",  numCamisa = 11, partidas = 30, gols = 14, assistencia = 9,  sobre = "Atacante colombiano de grande habilidade e velocidade. Díaz chegou ao Bayern em 2025 para reforçar o ataque alemão com sua criatividade e capacidade de driblar.", idTime = 5, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_luisdiaz
        Player("Michael Olise",  estrela = false, pais = "França",    numCamisa = 7,  partidas = 29, gols = 16, assistencia = 12, sobre = "Jovem atacante franco-inglês que se destacou pelo Crystal Palace e chegou ao Bayern com enorme expectativa. Olise é conhecido pelas suas finalizações de longa distância e dribles.", idTime = 5, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_olise
        Player("Joshua Kimmich", estrela = false, pais = "Alemanha",  numCamisa = 6,  partidas = 33, gols = 5,  assistencia = 14, sobre = "Símbolo do Bayern de Munique, Kimmich é considerado um dos melhores meio-campistas do mundo. Sua inteligência tática e liderança dentro de campo são incomparáveis.", idTime = 5, fotoResId = R.drawable.jogador_vini_teste), //, fotoResId = R.drawable.jogador_kimmich
        Player("Manuel Neuer",   estrela = false, pais = "Alemanha",  numCamisa = 1,  partidas = 27, gols = 0,  assistencia = 0,  sobre = "Lenda do futebol mundial, Neuer revolucionou a posição de goleiro com o conceito de 'goleiro-libero'. Capitão histórico do Bayern e da seleção alemã, ainda defende com maestria.", idTime = 5, fotoResId = R.drawable.jogador_vini_teste) //, fotoResId = R.drawable.jogador_neuer
    )

    // ── Helpers ─────────────────────────────────────────────────────────────
    fun getTeamById(id: Int): Team? = teams.find { it.id == id }

    fun getPlayersByTeam(idTime: Int): List<Player> = players.filter { it.idTime == idTime }

    fun getCoachByTeam(idTime: Int): Coach? = coaches.find { it.idTime == idTime }

    fun getStarPlayer(idTime: Int): Player? = players.find { it.idTime == idTime && it.estrela }
}