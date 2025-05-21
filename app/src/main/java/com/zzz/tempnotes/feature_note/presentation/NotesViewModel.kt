package com.zzz.tempnotes.feature_note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.tempnotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    private val _uiState = MutableStateFlow(NoteUIState())
    val uiState = _uiState.asStateFlow()

    private var id: Int = 0
    private var lastDeletedNote: Note? = null

    fun addNote() {

        viewModelScope.launch {
            id += 1
            val note = Note(id , _uiState.value.title , _uiState.value.body)

            _notes.update {
                it + note
            }
            clearState()
        }

    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            lastDeletedNote = note
            _notes.update {
                it.minus(note)
            }
        }
    }

    fun undoDelete() {
        viewModelScope.launch {
            lastDeletedNote?.let {
                _notes.update {
                    it + lastDeletedNote!!
                }
                clearLastDeleted()
            }
        }
    }
    fun clearLastDeleted(){
        lastDeletedNote = null
    }

    fun onTitleChange(value: String) {
        _uiState.update {
            it.copy(title = value)
        }
    }

    fun onBodyChange(value: String) {
        _uiState.update {
            it.copy(body = value)
        }
    }

    fun clearState() {
        viewModelScope.launch {
            _uiState.update {
                NoteUIState()
            }
        }
    }


}