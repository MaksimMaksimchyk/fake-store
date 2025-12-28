package com.example.fake_store.data.storage

import android.content.Context
import androidx.core.content.edit

object AuthManager {
    private const val PREFS_NAME = "APP_SETTINGS"
    private const val TOKEN_KEY = "auth_token"

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToken(context: Context, token: String) {
        getPrefs(context).edit { putString(TOKEN_KEY, token) }
    }

    fun getToken(context: Context): String? {
        return getPrefs(context).getString(TOKEN_KEY, null)
    }

}