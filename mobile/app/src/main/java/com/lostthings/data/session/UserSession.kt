package com.lostthings.data.session

import android.content.Context
import android.preference.PreferenceManager

class UserSession(context: Context) {

    private val sharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    var profilePhotoUrl: String
        get() = sharedPreferences.getString("profilePhoto", "") ?: ""
        set(value) = sharedPreferences.edit().putString("profilePhoto", value).apply()

    var profileName: String
        get() = sharedPreferences.getString("profileName", "") ?: ""
        set(value) = sharedPreferences.edit().putString("profileName", value).apply()

    var profileEmail: String
        get() = sharedPreferences.getString("profileEmail", "") ?: ""
        set(value) = sharedPreferences.edit().putString("profileEmail", value).apply()
}