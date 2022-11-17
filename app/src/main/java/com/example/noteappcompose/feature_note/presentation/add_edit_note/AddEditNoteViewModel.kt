package com.example.noteappcompose.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappcompose.feature_note.data.models.InvalidNoteException
import com.example.noteappcompose.feature_note.domain.entity.Note
import com.example.noteappcompose.feature_note.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases:NoteUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _stateNoteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter title..."
    ))
    val stateNoteTitle:State<NoteTextFieldState> = _stateNoteTitle

    private val _stateNoteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter some content..."
    ))
    val stateNoteContent:State<NoteTextFieldState> = _stateNoteContent

    private val _stateNoteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val stateNoteColor:State<Int> = _stateNoteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId:Int? = null

    init {
        savedStateHandle.get<Int>(NOTE_ID_KEY)?.let { noteId ->
            if (noteId != -1){
                viewModelScope.launch {
                    noteUseCases.getNoteUseCase(noteId)?.also { note->
                        currentNoteId = note.id
                        _stateNoteTitle.value = stateNoteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _stateNoteContent.value = stateNoteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _stateNoteColor.value = note.color
                    }
                }
            }
        }
    }

    companion object{
        const val NOTE_ID_KEY = "noteId"
    }

    fun onEvent(event: AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTitle -> {
                _stateNoteTitle.value = stateNoteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _stateNoteTitle.value = stateNoteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            stateNoteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _stateNoteContent.value = stateNoteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _stateNoteContent.value = stateNoteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            stateNoteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _stateNoteColor.value = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                title = stateNoteTitle.value.text,
                                content = stateNoteContent.value.text,
                                color = stateNoteColor.value,
                                timestamp = System.currentTimeMillis(),
                                id = currentNoteId ?: 0
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

}