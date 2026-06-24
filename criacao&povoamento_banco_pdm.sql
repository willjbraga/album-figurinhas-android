create table times(
id integer primary key,
nome varchar(50),
sigla varchar(3),
cor_primaria varchar(50),
cor_secundaria varchar(50),
num_vitoria int,
ano_ultima_vitoria varchar(15),
historia text,
pais varchar(50),
idioma varchar(50),
ano_criacao int,
curiosidade text
);

create table competicao(
nome varchar(50) PRIMARY KEY,
ano_competicao varchar(15)
pais_sede varchar(50),
cidade_sede varchar(50),
estadio varchar(50)
);

create table treinador(
nome varchar(50) PRIMARY KEY,
perfil text,
pais varchar(50),
id_time integer references times(id)
);


create table jogadores(
nome varchar(50) PRIMARY KEY,
estrela boolean,
pais varchar(50),
num_camisa int,
partidas int,
gols int,
assistencia int,
sobre text,
id_time integer references times(id)
);

-- DADOS

INSERT INTO competicao VALUES(
'UEFA Champions League',
'2026/2027',
'Espanha',
'Madri',
'Riyadh Air Metropolitano'
);


-- =========================
-- TIMES
-- =========================

/*
Times
1 - Real Madrid
2 - Barcelona
3 - PSG
4 - Milan
5 - Bayern de Munique
*/


INSERT INTO times VALUES (
1,
'Real Madrid',
'RMA',
'#FFFFFF', '#FEBE10',
15,
'2023/2024',
'Fundado em 1902 na cidade de Madrid, o Real Madrid tornou-se um dos clubes mais vitoriosos do mundo. 
Em 1920, recebeu do rei Alfonso XIII o título de "Real" ("Real" significa "Real da Coroa"). 
O clube dominou o futebol europeu nas décadas de 1950 e 1960, liderado por Alfredo Di Stéfano.',
'Espanha',
'Espanhol',
1902,
'É o clube com mais títulos da UEFA Champions League, sendo frequentemente chamado de "Rei da Europa".'
);

INSERT INTO times VALUES (
2,
'Barcelona',
'BAR',
'#004D98','#A50044',
5,
'2014/2015',
'Fundado em 1899 por Joan Gamper, o Barcelona cresceu como símbolo da identidade catalã. Seu lema, "Més que un club" 
("Mais que um clube"), reflete sua importância cultural e política para a região da Catalonia.',
'Espanha',
'Espanhol',
1899,
'O Barcelona revelou alguns dos maiores jogadores da história, incluindo Lionel Messi, 
formado em sua famosa academia, La Masia.'
);

INSERT INTO times VALUES (
3,
'PSG',
'PSG',
'#004170', '#DA291C',
2,
'2025/2026',
'O PSG nasceu em 1970 da fusão entre dois clubes da região de Paris. 
Durante décadas teve sucesso moderado, mas passou por uma transformação 
após a aquisição pelo Qatar Sports Investments em 2011, tornando-se uma potência do futebol europeu.',
'França',
'Francês',
1970,
'O PSG formou um dos ataques mais famosos do futebol recente com Lionel Messi, Neymar e Kylian Mbappé. Mesmo
com os 3 jogadores, não ganhou a Champions League.'
);

INSERT INTO times VALUES (
4,
'Milan',
'MIL',
'#FB090B', '#000000',
7,
'2006/2007',
'Fundado em 1899 por empresários ingleses e italianos na cidade de Milan, 
o Milan tornou-se um dos clubes mais tradicionais da Europa. 
O nome "Milan" foi mantido em inglês para destacar suas origens britânicas.',
'Itália',
'Italiano',
1899,
'O clube conquistou títulos europeus em diferentes eras, com craques como 
Paolo Maldini, que passou toda a carreira profissional no Milan e disputou mais de 900 partidas pelo clube.'
);

INSERT INTO times VALUES (
5,
'Bayern de Munique',
'FCB',
'#DC052D', '#FFFFFF',
6,
'2019/2020',
'Fundado em 1900 em Munich, o Bayern começou como um clube regional e 
tornou-se a principal força do futebol alemão. 
O clube dominou a Bundesliga especialmente a partir da década de 1970.',
'Alemanha',
'Alemão',
1900,
'Entre 2013 e 2023, o Bayern conquistou 11 títulos consecutivos da Bundesliga, 
uma das maiores sequências de campeonatos nacionais da história do futebol.'
);

-- =========================
-- TREINADORES
-- =========================

-- REAL MADRID

INSERT INTO treinador VALUES (
'Jose Mourinho',
'José Mourinho é um dos treinadores mais vitoriosos da história do futebol.
Conhecido pelo apelido de "The Special One", construiu sua carreira conquistando títulos nacionais e
internacionais por clubes de diferentes países. Sua principal característica é a forte organização defensiva, 
disciplina tática e capacidade de adaptar suas equipes aos adversários. 
Além do sucesso esportivo, destaca-se pela liderança forte, personalidade marcante e 
habilidade em gerir grandes elencos repletos de estrelas.',
'Portugal',
1
);

-- BARCELONA

INSERT INTO treinador VALUES (
'Hansi Flick',
'Hansi Flick é um treinador alemão reconhecido por implementar um futebol ofensivo, intenso e baseado em 
pressão alta. Ganhou destaque internacional após conquistar múltiplos títulos em uma única temporada, 
incluindo a UEFA Champions League. Suas equipes costumam apresentar alta posse de bola, movimentação constante e 
grande eficiência ofensiva. É considerado um dos principais representantes da moderna escola alemã de treinadores.',
'Alemanha',
2
);

-- PSG

INSERT INTO treinador VALUES (
'Luis Enrique',
'Luis Enrique é um treinador espanhol conhecido por seu estilo de jogo ofensivo e pelo forte controle da 
posse de bola. Adepto do futebol posicional, busca equipes organizadas tecnicamente e capazes de dominar as 
partidas através da circulação rápida da bola. Como treinador conquistou importantes títulos nacionais e 
internacionais, sendo reconhecido pela capacidade de desenvolver jovens talentos e potencializar o desempenho de jogadores de elite.',
'Espanha',
3
);

-- AC MILAN

INSERT INTO treinador VALUES (
'Ruben Amorim',
'Rúben Amorim é um treinador português considerado uma das maiores promessas da nova geração de técnicos europeus. 
Destaca-se pela utilização de sistemas táticos modernos, especialmente formações com três defensores, além de 
valorizar a intensidade, organização coletiva e transições rápidas. Sua capacidade de desenvolver jogadores jovens e 
criar equipes competitivas chamou a atenção do futebol europeu, tornando-o um dos treinadores mais respeitados da atualidade.',
'Portugal',
4
);

-- BAYERN DE MUNIQUE

INSERT INTO treinador VALUES (
'Vincent Kompany',
'Vincent Kompany é um ex-zagueiro belga que construiu uma carreira vitoriosa dentro de campo antes de 
migrar para a função de treinador. Como técnico, busca equipes organizadas, agressivas na recuperação 
da posse de bola e com forte participação ofensiva. Sua experiência como líder e capitão em alto nível influencia 
diretamente sua forma de gestão, valorizando disciplina, trabalho em equipe e mentalidade vencedora.',
'Bélgica',
5
);

-- =========================
-- JOGADORES - REAL MADRID
-- =========================

INSERT INTO jogadores VALUES (
'Vinicius Junior', TRUE, 'Brasil', 7,
320, 105, 80,
'Atacante brasileiro de grande velocidade,
habilidade no drible e capacidade de decisão em partidas importantes.',
1
);

INSERT INTO jogadores VALUES (
'Kylian Mbappe', FALSE, 'França', 9,
450, 320, 120,
'Atacante francês campeão mundial e um dos principais jogadores da atualidade.',
1
);

INSERT INTO jogadores VALUES (
'Jude Bellingham', FALSE, 'Inglaterra', 5,
250, 55, 45,
'Meio-campista inglês destaque pela qualidade técnica e visão de jogo.',
1
);

INSERT INTO jogadores VALUES (
'Federico Valverde', FALSE, 'Uruguai', 8,
350, 35, 40,
'Meio-campista uruguaio conhecido pela intensidade, resistência 
física e contribuição tanto defensiva quanto ofensiva.',
1
);

INSERT INTO jogadores VALUES (
'Thibaut Courtois', FALSE, 'Bélgica', 1,
500, 0, 0,
'Goleiro belga de elite, destaque pelos reflexos, posicionamento
e segurança sob as traves.'
1
);

-- =========================
-- JOGADORES - BARCELONA
-- =========================

INSERT INTO jogadores VALUES (
'Raphinha', TRUE, 'Brasil', 11,
280, 85, 70,
'Atacante brasileiro que se destaca pela criatividade, 
capacidade de assistência e eficiência ofensiva.',
2
);

INSERT INTO jogadores VALUES (
'Lamine Yamal', FALSE, 'Espanha', 10,
120, 30, 35,
'Jovem promessa espanhola considerada uma das maiores revelações do futebol.',
2
);

INSERT INTO jogadores VALUES (
'Robert Lewandowski', FALSE, 'Polonia', 9,
750, 500, 120,
'Centroavante polonês entre os maiores artilheiros do século.',
2
);

INSERT INTO jogadores VALUES (
'Pedri', FALSE, 'Espanha', 8,
220, 25, 35,
'Meio-campista espanhol conhecido pela criatividade e controle de jogo.',
2
);

INSERT INTO jogadores VALUES (
'Marc-Andre ter Stegen', FALSE, 'Alemanha', 1,
450, 0, 0,
'Goleiro alemão referência na saída de bola.',
2
);

-- =========================
-- JOGADORES - PSG
-- =========================

INSERT INTO jogadores VALUES (
'Ousmane Dembele', FALSE, 'Franca', 10,
350, 95, 95,
'Atacante francês conhecido pela velocidade e drible.',
3
);

INSERT INTO jogadores VALUES (
'Khvicha Kvaratskhelia', TRUE, 'Georgia', 7,
250, 80, 70,
'Extremo georgiano considerado um dos jogadores mais criativos da Europa.',
3
);

INSERT INTO jogadores VALUES (
'Vitinha', FALSE, 'Portugal', 17,
280, 25, 30,
'Meio-campista português peça fundamental na construção ofensiva.',
3
);

INSERT INTO jogadores VALUES (
'Marquinhos', FALSE, 'Brasil', 5,
500, 40, 15,
'Zagueiro brasileiro experiente, líder defensivo e 
referência técnica dentro da equipe.',
3
);

INSERT INTO jogadores VALUES (
'Matvey Safonov', FALSE, 'Russia', 39,
180, 0, 0,
'Goleiro russo contratado para reforçar a equipe francesa.',
3
);

-- =========================
-- JOGADORES - MILAN
-- =========================

INSERT INTO jogadores VALUES (
'Rafael Leao', FALSE, 'Portugal', 10,
280, 90, 75,
'Atacante português conhecido pela velocidade, 
força física e capacidade de criar oportunidades ofensivas.',
4
);

INSERT INTO jogadores VALUES (
'Christian Pulisic', FALSE, 'Estados Unidos', 11,
320, 80, 60,
'Atacante norte-americano com boa capacidade de criação.',
4
);

INSERT INTO jogadores VALUES (
'Luka Modric', TRUE, 'Croacia', 14,
850, 90, 140,
'Veterano meio-campista croata vencedor da Bola de Ouro.',
4
);

INSERT INTO jogadores VALUES (
'Mike Maignan', FALSE, 'Franca', 16,
300, 0, 0,
'Goleiro francês reconhecido por reflexos e liderança.',
4
);

INSERT INTO jogadores VALUES (
'Adrien Rabiot', FALSE, 'Franca', 8,
450, 55, 40,
'Meio-campista francês experiente.',
4
);

-- =========================
-- JOGADORES - BAYERN
-- =========================

INSERT INTO jogadores VALUES (
'Harry Kane', TRUE, 'Inglaterra', 9,
650, 430, 95,
'Centroavante inglês de classe mundial, reconhecido pela 
qualidade nas finalizações e inteligência de posicionamento.',
5
);

INSERT INTO jogadores VALUES (
'Luis Diaz', FALSE, 'Colombia', 14,
320, 90, 60,
'Atacante colombiano conhecido pela velocidade e habilidade.',
5
);

INSERT INTO jogadores VALUES (
'Michael Olise', FALSE, 'Franca', 17,
180, 40, 50,
'Extremo francês destaque pela criatividade e assistências.',
5
);

INSERT INTO jogadores VALUES (
'Joshua Kimmich', FALSE, 'Alemanha', 6,
550, 45, 110,
'Meio-campista alemão líder técnico da equipe.',
5
);

INSERT INTO jogadores VALUES (
'Manuel Neuer', FALSE, 'Alemanha', 1,
800, 0, 0,
'Um dos maiores goleiros da história do futebol.',
5
);