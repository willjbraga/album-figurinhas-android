A implementação do banco remoto precisa mexer na parte de 'Data' do projeto, além disso o 'Data/mock' vai ser excluído assim que a implementação do banco for feita, ela ta sendo usada só para testar o visual básico do aplicativo e verificar se o fluxo das telas se está funcionando.
Detalhe importante é que além dos atributos definidos pelo banco as data class 'coach', 'competition' e 'team' possuem um id para a foto que é 'R.drawable.nome_da_foto', esse valor tem q ser definido diretamente no código já que esse id não vem do banco.


A parte do design das telas basicamente vai mexer na pasta 'ui', nele tem as telas e os cards clicáveis do jogador estrela, jogador normal e técnico que são usados na tela do álbum do time,
um detalhe importante é que a imagem do troféu da tela inicial não esta como um id da data class 'competition' então tem que ser adicionado na tela em questão e referenciado, 
a mesma coisa para outras imagens que forem colocadas em telas de fundo que não são as fotos dos emblemas dos times, jogadores e técnicos. 
Abaixo é uma referência para o nome das imagens que precisam ser colocadas na pasta 'res/drawable/'


<!--  ════════════════════════════════════════════════════════════════════════
     REFERÊNCIA DE DRAWABLES (res/drawable/)
     Nomes que você deve usar ao adicionar as imagens ao projeto
     ════════════════════════════════════════════════════════════════════════

     TROFÉU
     ──────
     trofeu.png                      → troféu UEFA Champions League

     ESCUDOS DE TIMES
     ────────────────
     escudo_real_madrid.png
     escudo_barcelona.png
     escudo_psg.png
     escudo_milan.png
     escudo_bayern.png

     FOTOS DE TREINADORES
     ─────────────────────
     treinador_mourinho.png
     treinador_flick.png
     treinador_luisenrique.png
     treinador_amorim.png
     treinador_kompany.png

     FOTOS DE JOGADORES – Real Madrid
     ──────────────────────────────────
     jogador_vinicius.png
     jogador_mbappe.png
     jogador_bellingham.png
     jogador_valverde.png
     jogador_courtois.png

     FOTOS DE JOGADORES – Barcelona
     ────────────────────────────────
     jogador_raphinha.png
     jogador_yamal.png
     jogador_lewandowski.png
     jogador_pedri.png
     jogador_terstegen.png

     FOTOS DE JOGADORES – PSG
     ─────────────────────────
     jogador_dembele.png
     jogador_kvaratskhelia.png
     jogador_vitinha.png
     jogador_marquinhos.png
     jogador_safonov.png

     FOTOS DE JOGADORES – Milan
     ────────────────────────────
     jogador_leao.png
     jogador_pulisic.png
     jogador_modric.png
     jogador_maignan.png
     jogador_rabiot.png

     FOTOS DE JOGADORES – Bayern
     ─────────────────────────────
     jogador_kane.png
     jogador_luisdiaz.png
     jogador_olise.png
     jogador_kimmich.png
     jogador_neuer.png

     DICA: Coloque todas as imagens como PNG ou WebP
     diretamente em res/drawable/
     Após adicionar, o R.drawable.<nome> fica disponível
     automaticamente no código.
     ════════════════════════════════════════════════════════════════════════  -->
