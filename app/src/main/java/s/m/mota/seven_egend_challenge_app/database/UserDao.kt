package s.m.mota.seven_egend_challenge_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import s.m.mota.seven_egend_challenge_app.models.Message
import s.m.mota.seven_egend_challenge_app.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}