package s.m.mota.seven_egend_challenge_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import s.m.mota.seven_egend_challenge_app.database.Converters

@Entity(tableName = "messages")
@TypeConverters(Converters::class)
data class Message(
    @PrimaryKey(autoGenerate = false) @SerializedName("id") val id: Long,
    @SerializedName("userId") val userId: Long,
    @SerializedName("content") val content: String,
    @SerializedName("attachments") val attachments: List<Attachment>? = null
)