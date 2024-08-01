package s.m.mota.seven_egend_challenge_app.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import s.m.mota.seven_egend_challenge_app.models.Message
import s.m.mota.seven_egend_challenge_app.models.User

@androidx.room.Database(
    entities = [Message::class, User::class], version = 1, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class ApiDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: ApiDatabase

        fun init(context: Context) {
            synchronized(this) {
                val builder = Room.databaseBuilder(
                        context.applicationContext, ApiDatabase::class.java, "api_database"
                    )

                migrations.forEach { migration -> builder.addMigrations(migration) }
                INSTANCE = builder.build()
            }
        }

        fun getDatabase(): ApiDatabase {
            return INSTANCE
        }
    }
}
