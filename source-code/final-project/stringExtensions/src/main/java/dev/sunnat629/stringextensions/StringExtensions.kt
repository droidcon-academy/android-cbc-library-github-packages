package dev.sunnat629.stringextensions

import android.os.Build
import java.util.Base64

object StringExtensions {

    /**
     * Make the first letter of a string lower case.
     *
     * Usage:
     * "Abc".beginWithLowerCase() // => "abc"
     */
    fun String.beginWithLowerCase(): String {
        return when (this.length) {
            0 -> ""
            1 -> this.lowercase()
            else -> this[0].lowercase() + this.substring(1)
        }
    }

    /**
     * Make the first letter of a string upper case.
     *
     * Usage:
     * "abc".beginWithUpperCase() // => "Abc"
     */
    fun String.beginWithUpperCase(): String {
        return when (this.length) {
            0 -> ""
            1 -> this.uppercase()
            else -> this[0].uppercase() + this.substring(1)
        }
    }

    /**
     * Convert a snake case string to camel case.
     *
     * Usage:
     * "a_b_c".toCamelCase() // => "ABC"
     */
    fun String.toCamelCase(): String {
        return this.split('_').joinToString("") {
            it.beginWithUpperCase()
        }
    }

    /**
     * Convert a camel case string to snake case.
     *
     * Usage:
     * "ABC".toSnakeCase() // => "a_b_c"
     */
    fun String.toSnakeCase(): String {
        var text = ""
        var isFirst = true
        this.forEach {
            if (it.isUpperCase()) {
                if (isFirst) isFirst = false
                else text += "_"
                text += it.lowercase()
            } else {
                text += it
            }
        }
        return text
    }

    /**
     * Encode a string into Base64.
     * Requires SDK version O or higher.
     *
     * Usage:
     * "string".encodeIntoBase64() // => Encoded string
     */
    fun String?.encodeIntoBase64(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getUrlEncoder().encodeToString(this?.toByteArray())
        } else ""
    }

    /**
     * Decode a Base64 encoded string.
     * Requires SDK version O or higher.
     *
     * Usage:
     * "encodedString".decodeIntoBase64() // => Decoded string
     */
    fun String?.decodeIntoBase64(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(Base64.getUrlDecoder().decode(this))
        } else ""
    }

    /**
     * Reverse the characters in a string.
     *
     * Usage:
     * "Hello".reverseString() // => "olleH"
     */
    fun String.reverseString(): String = this.reversed()

    /**
     * Count the number of words in a string.
     *
     * Usage:
     * "Hello world".wordCount() // => 2
     */
    fun String.wordCount(): Int = this.trim().split("\\s+".toRegex()).size

    /**
     * Determine if a string contains only numeric characters.
     *
     * Usage:
     * "12345".isNumeric() // => true
     */
    fun String.isNumeric(): Boolean = this.all { it.isDigit() }

    /**
     * Check for a valid email format in a string.
     *
     * Usage:
     * "example@example.com".isValidEmail() // => true
     */
    fun String.isValidEmail(): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    /**
     * Convert each word's first letter to uppercase in a string.
     *
     * Usage:
     * "hello world".toTitleCase() // => "Hello World"
     */
    fun String.toTitleCase(): String = this.split(" ").joinToString(" ") { it.beginWithUpperCase() }

    /**
     * Abbreviate a string to a specified length with an ellipsis.
     *
     * Usage:
     * "This is a long string".abbreviate(10) // => "This is a..."
     */
    fun String.abbreviate(maxLength: Int): String =
        if (this.length <= maxLength) this else this.take(maxLength - 3) + "..."

    /**
     * Capitalize the first letter of each word in a string.
     *
     * Usage:
     * "hello world".capitalizeWords() // => "Hello World"
     */
    fun String.capitalizeWords(): String = this.split(" ").joinToString(" ") {
        it.lowercase().replaceFirstChar { replacedString ->
            if (replacedString.isLowerCase()) replacedString.titlecase()
            else replacedString.toString()
        }
    }

    /**
     * Check if a string is a palindrome.
     *
     * Usage:
     * "madam".isPalindrome() // => true
     */
    fun String.isPalindrome(): Boolean = this == this.reverseString()

    /**
     * Remove special characters from a string.
     *
     * Usage:
     * "Hello! How are you?".removeSpecialCharacters() // => "Hello How are you"
     */
    fun String.removeSpecialCharacters(): String = this.replace(Regex("[^A-Za-z0-9 ]"), "")
}