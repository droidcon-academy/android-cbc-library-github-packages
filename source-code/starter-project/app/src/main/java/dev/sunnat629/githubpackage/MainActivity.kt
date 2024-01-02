package dev.sunnat629.githubpackage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.sunnat629.githubpackage.ui.theme.GithubPackageTheme
import dev.sunnat629.stringextension.StringExtension
import dev.sunnat629.stringextension.StringExtension.encodeIntoBase64

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootView()
        }
    }
}

@Composable
fun RootView() {
    GithubPackageTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                StringExtensionDemo()
            }
        }
    }
}

@Composable
fun StringExtensionDemo() {
    val userName = "Mohi Us Sunnat"
    val password = "password".encodeIntoBase64()
    val email = "mohiexample.com"

    val color = if (StringExtension.isEmail(email)) Color.Black else Color.Red

    Text(text = "User Name: $userName")
    Spacer(modifier = Modifier.height(8.dp))

    Text(text = "Password: $password")
    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Email: $email",
        color = color
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun GithubPackagePreview() {
    RootView()
}