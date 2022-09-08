package com.example.profnotes.data.repository.core

import com.example.profnotes.data.model.util.ResponseWrapper
import com.example.profnotes.data.network.NotesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRepository {
    suspend inline fun <T> getResponseInResponseWrapper(response: Response<T>)
            : ResponseWrapper<T> {
        return withContext(Dispatchers.IO)
        {

            if (response.isSuccessful) {
                ResponseWrapper.Success(
                    body = response.body()!!
                )
            }else{
                    ResponseWrapper.Error(
                        code = response.code()
                    )
                }
            }
        }
    }
