package com.example.noteappcompose.feature_note.domain.usecase

import com.example.noteappcompose.feature_note.domain.entity.Note
import com.example.noteappcompose.feature_note.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note:Note){
        repository.deleteNote(note)
    }

}