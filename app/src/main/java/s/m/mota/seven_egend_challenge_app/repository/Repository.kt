package s.m.mota.seven_egend_challenge_app.repository

import s.m.mota.seven_egend_challenge_app.database.ApiDatabase
import s.m.mota.seven_egend_challenge_app.models.ConversationResponse
import s.m.mota.seven_egend_challenge_app.retrofit.ConversationsApi

class Repository() {
    private val database = ApiDatabase.getDatabase()
    private val localDatabaseSource = LocalDatabaseSource(database.messageDao(), database.userDao())
    private val apiDataSource = ApiDataSource(ConversationsApi)

    suspend fun getConversations(repositoryFetchStrategy: RepositoryFetchStrategy = RepositoryFetchStrategy.STANDARD): ConversationResponse {
        return repositoryFetchStrategy.fetch(localDatabaseSource, apiDataSource)
    }
}
