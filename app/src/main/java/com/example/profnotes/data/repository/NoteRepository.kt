package com.example.profnotes.data.repository

import com.example.profnotes.data.model.Note
import com.example.profnotes.data.model.NoteResponse
import com.example.profnotes.data.model.util.ResponseWrapper
import com.example.profnotes.data.network.NotesApi
import com.example.profnotes.data.repository.core.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteApi:NotesApi
): BaseRepository() {
    suspend fun getNote(): ResponseWrapper<NoteResponse> = getResponseInResponseWrapper(noteApi.getNote())

}