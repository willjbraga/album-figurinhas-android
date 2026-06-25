import androidx.room.*

@Dao
interface CompeticaoDao {

    // ── Leitura:

    @Query("SELECT * FROM competicao")
    suspend fun getAll(): List<Competicao>

    @Query("SELECT * FROM competicao WHERE nome = :nome")
    suspend fun getByNome(nome: String): Competicao?

    @Query("SELECT * FROM competicao WHERE pais_sede = :pais")
    suspend fun getByPaisSede(pais: String): List<Competicao>

    @Query("SELECT * FROM competicao WHERE cidade_sede = :cidade")
    suspend fun getByCidadeSede(cidade: String): List<Competicao>

    @Query("SELECT * FROM competicao WHERE ano_competicao = :ano")
    suspend fun getByAno(ano: String): List<Competicao>

    @Query("SELECT * FROM competicao ORDER BY nome ASC")
    suspend fun getAllOrderByNome(): List<Competicao>

    // ── Escrita:

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(competicao: Competicao)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(competicoes: List<Competicao>)

    @Update
    suspend fun update(competicao: Competicao)

    @Delete
    suspend fun delete(competicao: Competicao)

    @Query("DELETE FROM competicao WHERE nome = :nome")
    suspend fun deleteByNome(nome: String)

    @Query("DELETE FROM competicao")
    suspend fun deleteAll()
}
