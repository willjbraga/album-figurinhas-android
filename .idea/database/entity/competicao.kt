import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competicao")
data class Competicao(
    @PrimaryKey
    val nome: String,
    val ano_competicao: String,
    val pais_sede: String,
    val cidade_sede: String,
    val estadio: String
)