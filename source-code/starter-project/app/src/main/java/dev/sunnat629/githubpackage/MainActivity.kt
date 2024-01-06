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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                EmailValidatorUI()
            }
        }
    }
}

@Composable
fun EmailValidatorUI() {
    val email = "example@email.com" // Replace with your email
    val validationText = remember { mutableStateOf("Validate the emailId") }
    val validationColor = remember { mutableStateOf(Color.Black) }

    Column {
        Text(
            text = email,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = validationText.value, color = validationColor.value)
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            if (StringExtension.isEmail(email)) {
                validationText.value = "A valid emailId!!!"
                validationColor.value = Color.Green
            } else {
                validationText.value = "An invalid emailId!!!"
                validationColor.value = Color.Red
            }
        }) {
            Text(text = "Validate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GithubPackagePreview() {
    RootView()
}