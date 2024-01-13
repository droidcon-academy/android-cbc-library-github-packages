package dev.sunnat629.stringextension

import android.os.Build
import java.util.Base64


object StringExtension {

    fun isEmail(emailId: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
    }

    fun String?.encodeIntoBase64(): String? {
        if (this == null) return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getUrlEncoder().encodeToString(this.toByteArray())
        } else this.toByteArray().toString()
    }
}