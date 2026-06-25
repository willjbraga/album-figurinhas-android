import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "treinador",
    foreignKeys = [ForeignKey(
        entity = times::class,
        parentColumns = ["id"],
        childColumns = ["id_time"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Treinador(
    @PrimaryKey
    val nome: String,
    val perfil: String,
    val pais: String,
    val id_time: Int
)