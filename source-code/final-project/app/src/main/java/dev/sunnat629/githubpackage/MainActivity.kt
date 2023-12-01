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
import dev.sunnat629.stringextensions.StringExtensions.abbreviate
import dev.sunnat629.stringextensions.StringExtensions.beginWithLowerCase
import dev.sunnat629.stringextensions.StringExtensions.beginWithUpperCase
import dev.sunnat629.stringextensions.StringExtensions.capitalizeWords
import dev.sunnat629.stringextensions.StringExtensions.decodeIntoBase64
import dev.sunnat629.stringextensions.StringExtensions.encodeIntoBase64
import dev.sunnat629.stringextensions.StringExtensions.isNumeric
import dev.sunnat629.stringextensions.StringExtensions.isPalindrome
import dev.sunnat629.stringextensions.StringExtensions.isValidEmail
import dev.sunnat629.stringextensions.StringExtensions.removeSpecialCharacters
import dev.sunnat629.stringextensions.StringExtensions.reverseString
import dev.sunnat629.stringextensions.StringExtensions.toCamelCase
import dev.sunnat629.stringextensions.StringExtensions.toSnakeCase
import dev.sunnat629.stringextensions.StringExtensions.toTitleCase
import dev.sunnat629.stringextensions.StringExtensions.wordCount

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubPackageTheme {
                // A surface container using the 'background' color from the theme
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
        "Begin With Lower Case:\n${exampleString.beginWithLowerCase()}",
        "Begin With Upper Case:\n${exampleString.beginWithUpperCase()}",
        "To Camel Case:\n${snakeCaseExample.toCamelCase()}",
        "To Snake Case:\n${camelCaseExample.toSnakeCase()}",
        "Encode Into Base64:\n${exampleString.encodeIntoBase64()}",
        "Decode Base64:\n${exampleString.encodeIntoBase64().decodeIntoBase64()}",
        "Reverse String:\n${exampleString.reverseString()}",
        "Word Count:\n${exampleString.wordCount()}",
        "Is Numeric (\"12345\"):\n${"12345".isNumeric()}",
        "Is Valid Email ($emailExample):\n${emailExample.isValidEmail()}",
        "To Title Case (\"hello world\"):\n${"hello world".toTitleCase()}",
        "Abbreviate (\"This is a long string for abbreviation\"):\n${longStringExample.abbreviate(10)}",
        "Capitalize Words (\"hello world\"):\n${"hello world".capitalizeWords()}",
        "Is Palindrome (madam):\n${"madam".isPalindrome()}",
        "Remove Special Characters: \n${exampleString.removeSpecialCharacters()}"
        // Add other results as needed
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