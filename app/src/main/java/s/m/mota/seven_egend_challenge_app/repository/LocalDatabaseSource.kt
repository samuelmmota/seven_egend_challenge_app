package s.m.mota.seven_egend_challenge_app.repository

import  s.m.mota.seven_egend_challenge_app.database.MessageDao
import s.m.mota.seven_egend_challenge_app.database.UserDao
import s.m.mota.seven_egend_challenge_app.models.ConversationResponse
import s.m.mota.seven_egend_challenge_app.models.Message
import s.m.mota.seven_egend_challenge_app.models.User

class LocalDatabaseSource(private val messageDao: MessageDao, private val userDao: UserDao) :
    DataSourceInterface {

    override suspend fun fetch(): ConversationResponse {
        val messages = messageDao.getAllMessages()
        val users = userDao.getAllUsers()

        return ConversationResponse(messages, users)
    }

    suspend fun insertMessages(vararg message: Message) {
        messageDao.insertMessage(*message)
    }

    suspend fun insertUsers(vararg user: User) {
        userDao.insertUser(*user)
    }
}