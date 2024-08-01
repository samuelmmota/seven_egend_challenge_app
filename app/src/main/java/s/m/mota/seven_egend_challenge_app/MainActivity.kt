package s.m.mota.seven_egend_challenge_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import s.m.mota.viewmodel.ViewModel
import s.m.mota.seven_egend_challenge_app.database.ApiDatabase
import s.m.mota.seven_egend_challenge_app.ui.screens.ChatScreen
import s.m.mota.seven_egend_challenge_app.ui.theme.SevenEgendChallengeApplicationTheme


class MainActivity : ComponentActivity() {
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiDatabase.init(applicationContext)
        setContent {
            SevenEgendChallengeApplicationTheme {
                ChatScreen(viewModel)
            }
        }
    }
}

