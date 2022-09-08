package com.example.profnotes.core.di

import android.content.Context
import com.example.profnotes.data.local.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): Prefs =
        Prefs(context)
}