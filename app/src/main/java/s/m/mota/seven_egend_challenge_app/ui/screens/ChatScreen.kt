package s.m.mota.seven_egend_challenge_app.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import s.m.mota.seven_egend_challenge_app.models.Attachment
import s.m.mota.seven_egend_challenge_app.models.Message
import s.m.mota.seven_egend_challenge_app.models.User
import s.m.mota.viewmodel.ViewModel
import coil.compose.LocalImageLoader
import s.m.mota.seven_egend_challenge_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(viewModel: ViewModel) {
    val messages by viewModel.messages.collectAsState()
    val users by viewModel.users.collectAsState()
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messages) { message ->
                val user = users.find { it.id == message.userId }
                MessageItem(message, user)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BasicTextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {}) {
                Text("Send")
            }
        }
    }
}

@Composable
fun MessageItem(message: Message, user: User?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        user?.let {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current).data(user.avatarId).apply {
                    error(R.drawable.baseline_person_24)
                }.build(), imageLoader = LocalImageLoader.current
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = user?.name ?: "Unknown",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Blue
            )
            Text(
                text = message.content, style = MaterialTheme.typography.bodyLarge
            )

            message.attachments?.let { attachments ->
                if (attachments.isNotEmpty()) {
                    Log.e("TAG ERROR", attachments.toString())
                    AttachmentsView(attachments)
                } else {
                    Log.e("TAG ERROR", attachments.toString())
                }
            }
        }
    }
}

@Composable
fun AttachmentsView(attachments: List<Attachment?>) {
    Column(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        attachments.forEach { attachment ->
            attachment?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    attachment.thumbnailUrl?.let {
                        val painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(attachment.thumbnailUrl).apply {
                                    error(R.drawable.ic_launcher_foreground)
                                }.build(), imageLoader = LocalImageLoader.current
                        )

                        Image(
                            painter = painter,
                            contentDescription = attachment.title,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        attachment.title?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        }
                        attachment.url?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}
