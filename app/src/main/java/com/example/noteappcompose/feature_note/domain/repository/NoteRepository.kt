package com.example.noteappcompose.feature_note.domain.repository

import com.example.noteappcompose.feature_note.data.models.NoteDbModel
import com.example.noteappcompose.feature_note.domain.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes():Flow<List<Note>>

    suspend fun getNoteById(id:Int):Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}