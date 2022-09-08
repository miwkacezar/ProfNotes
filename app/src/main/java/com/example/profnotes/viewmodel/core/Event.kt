package com.example.profnotes.viewmodel.core

sealed class Event{
    object Idel: Event()

    data class Toast(
        val message: String
    ): Event()
}
