package s.m.mota.seven_egend_challenge_app.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attachment(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String? = null
) : Serializable