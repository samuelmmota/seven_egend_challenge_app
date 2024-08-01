package s.m.mota.seven_egend_challenge_app.models

import com.google.gson.annotations.SerializedName

data class ConversationResponse(
    @SerializedName("messages") val messages: List<Message>,
    @SerializedName("users") val users: List<User>
)