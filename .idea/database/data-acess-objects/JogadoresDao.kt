import androidx.room.*

@Dao
interface JogadoresDao {

    // ── Leitura:

    @Query("SELECT * FROM jogadores")
    suspend fun getAll(): List<Jogadores>

    @Query("SELECT * FROM jogadores WHERE nome = :nome")
    suspend fun getByNome(nome: String): Jogadores?

    @Query("SELECT * FROM jogadores WHERE id_time = :idTime")
    suspend fun getByTime(idTime: Int): List<Jogadores>

    @Query("SELECT * FROM jogadores WHERE estrela = 1")
    suspend fun getEstrelas(): List<Jogadores>

    @Query("SELECT * FROM jogadores WHERE estrela = 1 AND id_time = :idTime")
    suspend fun getEstrelasByTime(idTime: Int): List<Jogadores>

    @Query("SELECT * FROM jogadores WHERE pais = :pais")
    suspend fun getByPais(pais: String): List<Jogadores>

    @Query("SELECT * FROM jogadores WHERE num_camisa = :numero AND id_time = :idTime")
    suspend fun getByCamisaETime(numero: Int, idTime: Int): Jogadores?

    @Query("SELECT * FROM jogadores ORDER BY gols DESC")
    suspend fun getAllOrderByGols(): List<Jogadores>

    @Query("SELECT * FROM jogadores ORDER BY partidas DESC")
    suspend fun getAllOrderByPartidas(): List<Jogadores>

    @Query("SELECT * FROM jogadores ORDER BY assistencia DESC")
    suspend fun getAllOrderByAssistencias(): List<Jogadores>

    // Busca o jogador junto com os dados completos do time (JOIN)
    @Query("""
        SELECT jogadores.*, times.nome AS nome_time, times.sigla, times.cor_primaria
        FROM jogadores
        INNER JOIN times ON jogadores.id_time = times.id
        WHERE jogadores.nome = :nome
    """)
    suspend fun getJogadorComTime(nome: String): Map<Jogadores, Times>

    // Todos os jogadores de um time com dados do time (JOIN)
    @Query("""
        SELECT jogadores.*, times.nome AS nome_time, times.sigla, times.cor_primaria
        FROM jogadores
        INNER JOIN times ON jogadores.id_time = times.id
        WHERE jogadores.id_time = :idTime
        ORDER BY jogadores.num_camisa ASC
    """)
    suspend fun getJogadoresComTimeByTime(idTime: Int): Map<Jogadores, Times>

    // ── Escrita:

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jogador: Jogadores)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(jogadores: List<Jogadores>)

    @Update
    suspend fun update(jogador: Jogadores)

    @Delete
    suspend fun delete(jogador: Jogadores)

    @Query("DELETE FROM jogadores WHERE nome = :nome")
    suspend fun deleteByNome(nome: String)

    @Query("DELETE FROM jogadores WHERE id_time = :idTime")
    suspend fun deleteAllFromTime(idTime: Int)

    @Query("DELETE FROM jogadores")
    suspend fun deleteAll()
}
