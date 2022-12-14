package com.example.noteappcompose.feature_note.presentation.notes

import com.example.noteappcompose.feature_note.domain.entity.Note
import com.example.noteappcompose.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder:NoteOrder):NotesEvent()
    data class DeleteNote(val note: Note):NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}


