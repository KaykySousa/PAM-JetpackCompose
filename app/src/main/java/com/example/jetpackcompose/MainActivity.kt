package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("HELLO WORLD FROM JETPACK COMPOSER")
            MessageCard("Kayky de Sousa")
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name")
}

@Preview
@Composable
fun PreviewMessageCard() {
    val name = "preview"
    Text(text = "Hello $name")
}