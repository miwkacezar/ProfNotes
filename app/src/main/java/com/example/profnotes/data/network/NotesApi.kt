package com.example.profnotes.data.network

import com.example.profnotes.data.model.NoteResponse
import retrofit2.Response
import retrofit2.http.GET

interface NotesApi {
    @GET("notes")
    fun getNotes(): NoteResponse

    @GET("todos/1")
    suspend fun getNote() :Response<NoteResponse>
}