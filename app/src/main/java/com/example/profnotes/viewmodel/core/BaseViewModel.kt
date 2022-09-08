package com.example.profnotes.viewmodel.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profnotes.data.model.util.ResponseWrapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _eventState = MutableStateFlow<Event>(Event.Idel)
    val eventState = _eventState.asStateFlow()

    @Suppress("unused")
    protected fun setIsLoading(value: Boolean) {
        _isLoading.value = value
    }

    protected fun launchSafety(
        errHandler: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit,
    ) {
        _isLoading.value = true
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            errHandler?.invoke(throwable) ?: errHandler.apply {
                _eventState.value = Event.Toast(throwable.message.toString())
                _eventState.value = Event.Idel
                Log.e("Error!", throwable.message.toString())
            }
        }
        (viewModelScope+errorHandler).launch(Dispatchers.IO) {
            block()
        }.invokeOnCompletion { _isLoading.value = false }
    }
}