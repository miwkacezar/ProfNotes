package com.example.profnotes.data.model.util

sealed class ResponseWrapper<out T>{

    data class Error<out E>(val code: Int): ResponseWrapper<E>()

    data class  Success<out S>(val body: S): ResponseWrapper<S>()

    class Idle<out T>: ResponseWrapper<T>()
}


