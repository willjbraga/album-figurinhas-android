import androidx.room.*

@Dao
interface TimesDao {

    // ── Leitura:

    @Query("SELECT * FROM times")
    suspend fun getAll(): List<Times>

    @Query("SELECT * FROM times WHERE id = :id")
    suspend fun getById(id: Int): Times?

    @Query("SELECT * FROM times WHERE nome LIKE '%' || :nome || '%'")
    suspend fun getByNome(nome: String): List<Times>

    @Query("SELECT * FROM times WHERE pais = :pais")
    suspend fun getByPais(pais: String): List<Times>

    @Query("SELECT * FROM times ORDER BY num_vitoria DESC")
    suspend fun getAllOrderByVitorias(): List<Times>

    // ── Escrita:

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(time: Times)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(times: List<Times>)

    @Update
    suspend fun update(time: Times)

    @Delete
    suspend fun delete(time: Times)

    @Query("DELETE FROM times WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM times")
    suspend fun deleteAll()
}
