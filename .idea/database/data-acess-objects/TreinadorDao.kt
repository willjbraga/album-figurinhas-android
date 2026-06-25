import androidx.room.*

@Dao
interface TreinadorDao {

    // ── Leitura:

    @Query("SELECT * FROM treinador")
    suspend fun getAll(): List<Treinador>

    @Query("SELECT * FROM treinador WHERE nome = :nome")
    suspend fun getByNome(nome: String): Treinador?

    @Query("SELECT * FROM treinador WHERE pais = :pais")
    suspend fun getByPais(pais: String): List<Treinador>

    @Query("SELECT * FROM treinador WHERE id_time = :idTime")
    suspend fun getByTime(idTime: Int): Treinador?

    // Busca o treinador junto com os dados completos do time (JOIN)
    @Query("""
        SELECT treinador.*, times.nome AS nome_time, times.sigla 
        FROM treinador 
        INNER JOIN times ON treinador.id_time = times.id
        WHERE treinador.nome = :nome
    """)
    suspend fun getTreinadorComTime(nome: String): Map<Treinador, Times>

    @Query("SELECT * FROM treinador ORDER BY nome ASC")
    suspend fun getAllOrderByNome(): List<Treinador>

    // ── Escrita:

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(treinador: Treinador)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(treinadores: List<Treinador>)

    @Update
    suspend fun update(treinador: Treinador)

    @Delete
    suspend fun delete(treinador: Treinador)

    @Query("DELETE FROM treinador WHERE nome = :nome")
    suspend fun deleteByNome(nome: String)

    @Query("DELETE FROM treinador")
    suspend fun deleteAll()
}
