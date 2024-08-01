package s.m.mota.seven_egend_challenge_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false) @SerializedName("id") val id: Long,
    @SerializedName("name") var name: String,
    @SerializedName("avatarId") var avatarId: String
)
