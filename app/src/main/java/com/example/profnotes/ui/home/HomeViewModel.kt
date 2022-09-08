package com.example.profnotes.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profnotes.data.model.Note
import com.example.profnotes.data.model.NoteResponse
import com.example.profnotes.data.model.util.ResponseWrapper
import com.example.profnotes.data.repository.NoteRepository
import com.example.profnotes.viewmodel.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : BaseViewModel()  {
    private var _note = MutableStateFlow<ResponseWrapper<NoteResponse>>(ResponseWrapper.Idle())
    val note = _note.asStateFlow()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getNote() {
        launchSafety(
            errHandler = {
                if (it is NullPointerException) Log.e("lol", "lol")
            }
        ) {
            _note.value =  noteRepository.getNote()
        }
    }
}