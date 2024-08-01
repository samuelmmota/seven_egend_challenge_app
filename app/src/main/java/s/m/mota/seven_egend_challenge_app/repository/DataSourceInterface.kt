package s.m.mota.seven_egend_challenge_app.repository

import s.m.mota.seven_egend_challenge_app.models.ConversationResponse

interface DataSourceInterface {
    suspend fun fetch(): ConversationResponse
}
