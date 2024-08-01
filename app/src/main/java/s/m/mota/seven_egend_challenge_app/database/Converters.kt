package s.m.mota.seven_egend_challenge_app.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import s.m.mota.seven_egend_challenge_app.models.Attachment

class Converters {
    private companion object {
        const val SERIALIZATION_DELIMITER = "&"
    }

    @TypeConverter
    fun fromAttachmentList(attachments: List<Attachment>?): String {
        var serialized = ""
        attachments?.forEach {
            run {
                serialized += (Gson().toJson(it) + SERIALIZATION_DELIMITER)
            }
        }

        return serialized.dropLast(1)
    }

    @TypeConverter
    fun toAttachmentList(serializedAttachments: String): List<Attachment> {
        val split = serializedAttachments.split(SERIALIZATION_DELIMITER)
        val output = ArrayList<Attachment>()
        split.forEach {
            run {
                output.add(Gson().fromJson(it, Attachment::class.java))
            }
        }
        return output
    }
}