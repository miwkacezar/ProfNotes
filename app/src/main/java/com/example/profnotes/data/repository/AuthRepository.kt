package com.example.profnotes.data.repository

import com.example.profnotes.data.local.Prefs
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val prefs: Prefs) {
    fun getIsFirstEnter() = prefs.isFirstEnter

    fun setIsFirstEnter(value: Boolean) {
      prefs.isFirstEnter  = value
    }
}