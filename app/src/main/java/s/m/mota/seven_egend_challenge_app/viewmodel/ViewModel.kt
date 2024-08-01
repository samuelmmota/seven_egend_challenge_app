package s.m.mota.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import s.m.mota.seven_egend_challenge_app.models.Message
import s.m.mota.seven_egend_challenge_app.models.User
import s.m.mota.seven_egend_challenge_app.repository.Repository

class ViewModel : ViewModel() {
    private val repository = Repository()
    val messages = MutableStateFlow<List<Message>>(emptyList())
    val users = MutableStateFlow<List<User>>(emptyList())

    init {
        fetchConversations()
    }

    private fun fetchConversations() {
        viewModelScope.launch {
            try {
                val response = repository.getConversations()
                messages.value = response.messages
                users.value = response.users
                for (message in messages.value) {
                    Log.e("TAG ERROR", message.toString())
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}
