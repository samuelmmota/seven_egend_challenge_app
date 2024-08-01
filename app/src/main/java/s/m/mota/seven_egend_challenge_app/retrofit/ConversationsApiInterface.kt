package s.m.mota.seven_egend_challenge_app.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import s.m.mota.seven_egend_challenge_app.models.ConversationResponse

interface ConversationsApiInterface {
    @GET("conversation")
    suspend fun getConversations(): ConversationResponse?
}

object ConversationsApi {
    private const val BASE_URL = "https://private-edd460-7egendchallengeandroid.apiary-mock.com/"


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    val apiService: ConversationsApiInterface by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ConversationsApiInterface::class.java)
    }

    suspend fun getConversations(): ConversationResponse? {
        return apiService.getConversations()
    }
}