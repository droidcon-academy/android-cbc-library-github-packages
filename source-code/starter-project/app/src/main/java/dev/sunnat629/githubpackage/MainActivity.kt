package dev.sunnat629.githubpackage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.sunnat629.githubpackage.ui.theme.GithubPackageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubPackageTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StringExtensionDemo()
                }
            }
        }
    }
}

@Composable
fun StringExtensionDemo() {
    val exampleString = "Hello, Jetpack Compose!"
    val snakeCaseExample = "hello_jetpack_compose"
    val camelCaseExample = "HelloJetpackCompose"
    val longStringExample = "This is a long string for abbreviation"
    val emailExample = "example@example.com"

    val results = listOf(
        "Original:\n$exampleString",
        "snakeCaseExample:\n$snakeCaseExample",
        "camelCaseExample:\n$camelCaseExample",
        "longStringExample:\n$longStringExample",
        "emailExample:\n$emailExample"
    )

    LazyColumn {
        items(results.size) { index ->
            Text(text = results[index], modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubPackageTheme {
        StringExtensionDemo()
    }
}