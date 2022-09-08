package com.example.profnotes.core.di

import androidx.navigation.Navigator
import com.example.profnotes.BuildConfig
import com.example.profnotes.data.network.NotesApi
import com.example.profnotes.data.network.api.RegisterApi
import com.example.profnotes.data.network.interceptor.AuthenticationInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val AUTH_OKHTTP_CLIENT = "okHttpClient"
    private const val WITHOUT_AUTH_OKHTTP_CLIENT = "okHttpClient"

    @Provides
    fun provideLogger() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Named(AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideAuthOkHttpClient(logger: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(AuthenticationInterceptor())
        .addInterceptor(logger)
        .build()

    @Named(WITHOUT_AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideWithoutOkHttpClient(logger: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(AuthenticationInterceptor())
        .build()

    @Provides
    fun provideMoshi() = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Named(AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideAutRetrofit(
        @Named(AUTH_OKHTTP_CLIENT)
        okHttpClient: OkHttpClient, moshi: Moshi
    ) = Retrofit.Builder()

        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Named(WITHOUT_AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideWithoutAutRetrofit(
        @Named(WITHOUT_AUTH_OKHTTP_CLIENT)
        okHttpClient: OkHttpClient, moshi: Moshi
    ) = Retrofit.Builder()

        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideNotesApi(
        @Named(AUTH_OKHTTP_CLIENT) retrofit: Retrofit
    ) = retrofit.create(NotesApi::class.java)

    @Provides
    fun registerNotesApi(
        @Named(AUTH_OKHTTP_CLIENT) retrofit: Retrofit
    ) = retrofit.create(RegisterApi::class.java)
}