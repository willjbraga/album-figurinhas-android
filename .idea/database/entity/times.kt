import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "times")
data class Times(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val sigla: String,
    val cor_primaria: String,
    val cor_secundaria: String,
    val num_vitoria: Int,
    val ano_ultima_vitoria: String,
    val historia: String,
    val pais: String,
    val idioma: String,
    val ano_criacao: Int,
    val curiosidade: String
)