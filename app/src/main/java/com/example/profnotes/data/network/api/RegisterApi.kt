package com.example.profnotes.data.network.api

import com.example.profnotes.data.model.RegisterResponse
import retrofit2.http.POST

interface RegisterApi {
    @POST("register")
    fun postRegister(): RegisterResponse
}