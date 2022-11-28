package com.example.jetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.tutorial.SampleData
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.ui.theme.shapeScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Função responsável por renderizar a lista de Conversas
        setContent {
            JetpackComposeTheme() {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

// Responsável por definir o formato da mensagem que será passada como parâmetro para o componente MessageCard
data class Message(val author: String, val body: String)

// Criação de componente MessageCard
@Composable
fun MessageCard(msg: Message) {

    // Função Row para manter os elementos em linha
    Row(modifier = Modifier.padding(all = 8.dp)) {

        // Função responsável por renderizar uma imagem circular de 40 dp de diâmetro
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Função responsável por adicionar um espaçamentro entre a imagem e a coluna com o nome do usuário e as mensagens
        Spacer(modifier = Modifier
            .width(8.dp))

        // Variável para verificar se a mensagem está expandinda
        var isExpanded by remember {
            mutableStateOf(false)
        }

        // Animação da cor da mensagem
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        // Função responsável por manter o nome de usuário e as mensagens em coluna
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

            // Função de texto para nome de usuário
            Text(
                text = msg.author,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )

            // Função responsável por adicionar um espaçamentro entre o nome do usuário e as mensagens
            Spacer(modifier = Modifier
                .height(4.dp))

            // Função responsável pela mensagem
            Surface(
                shape = MaterialTheme.shapeScheme.medium,
                tonalElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {

                // Função de texto para mensagem
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// Criação do componente Conversation, responsável por renderizar uma lista de MessageCard
@Composable
fun Conversation(messages: List<Message>) {

    // Função por manter as mensagens em coluna
    LazyColumn {

        // Função responsável por renderizar cada MessageCard
        messages.map {
            item {
                MessageCard(it)
            }
        }
    }
}


// Função de preview do componente Conversation
@Preview
@Composable
fun PreviewConversation() {
    JetpackComposeTheme {
        Conversation(SampleData.conversationSample)
    }
}

// Função de preview do componente MessageCard em light mode e dark mode
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Preview
@Composable
fun PreviewMessageCard() {
    JetpackComposeTheme() {
        Surface {
            MessageCard(msg = Message("Kayky de Sousa", "The Jetpack is good!"))
        }
    }
}