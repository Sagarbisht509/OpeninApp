package com.example.openinapp.utils

import android.content.Context
import com.example.openinapp.utils.Constants.PREFERENCE_FILE_kEY
import com.example.openinapp.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private var sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_kEY, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(USER_TOKEN, token).apply()
    }

    fun getToken() : String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }
}