package com.rechit.share.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val IS_FIRST_TIME_LAUNCH = booleanPreferencesKey("theme_setting")

    fun isFirstTimeLaunch(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] ?: true
        }
    }

    suspend fun setFirstTimeLaunch(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] = isFirstTime
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}