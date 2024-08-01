package s.m.mota.seven_egend_challenge_app.repository

import android.util.Log
import s.m.mota.seven_egend_challenge_app.models.ConversationResponse
import s.m.mota.seven_egend_challenge_app.models.Message
import s.m.mota.seven_egend_challenge_app.models.User
import s.m.mota.seven_egend_challenge_app.retrofit.ConversationsApi

class ApiDataSource(private val conversationsApi: ConversationsApi) : DataSourceInterface {
    private val TAG = "ApiDataSource"

    override suspend fun fetch(): ConversationResponse {
        return try {
            conversationsApi.getConversations()!!
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error. Refresh unsuccessful.", e)
            ConversationResponse(emptyList<Message>(), emptyList<User>())
        }
    }
}
