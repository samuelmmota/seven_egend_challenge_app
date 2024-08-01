package s.m.mota.seven_egend_challenge_app.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import s.m.mota.seven_egend_challenge_app.models.Message

@androidx.room.Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(vararg message: Message)

    @Query("SELECT * FROM messages")
    /*ORDER BY id */
    suspend fun getAllMessages(): List<Message>
}
