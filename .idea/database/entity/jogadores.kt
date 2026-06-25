import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jogadores",
        foreignKeys = [ForeignKey(
            entity = times::class,
            parentColumns = ["id"],
            childColumns = ["id_time"],
            onDelete = ForeignKeys.CASCADE
        ])
)

.addTypeConverter(...)

data class Jogadores(
    @PrimaryKey
    val nome: String,
    val estrela: Boolean,
    val pais: String,
    val num_camisa: Int,
    val partidas: Int,
    val gols: Int,
    val assistencias: Int,
    val sobre: String,

)
