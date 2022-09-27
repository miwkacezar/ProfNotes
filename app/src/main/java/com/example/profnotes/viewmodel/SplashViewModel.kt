package com.example.profnotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profnotes.data.local.Prefs
import com.example.profnotes.data.model.Note
import com.example.profnotes.data.model.NoteResponse
import com.example.profnotes.data.model.util.ResponseWrapper
import com.example.profnotes.data.repository.AuthRepository
import com.example.profnotes.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val noteRepository: NoteRepository
    ): ViewModel() {

    private var _note = MutableStateFlow<ResponseWrapper<Note>>(ResponseWrapper.Idle())
    val note = _note.asStateFlow()

    fun getIsFirstEnter() = authRepository.getIsFirstEnter()

    fun setIsFirstEnter(value: Boolean) {
        authRepository.setIsFirstEnter(value)
    }

    fun getNote(){
        viewModelScope.launch {
            _note.value = noteRepository.getNote()
        }
    }
}