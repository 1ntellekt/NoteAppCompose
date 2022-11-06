package com.example.noteappcompose.feature_note.data.repository

import com.example.noteappcompose.feature_note.data.data_sourse.NoteDao
import com.example.noteappcompose.feature_note.domain.entity.Note
import com.example.noteappcompose.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(dao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteById(id: Int): Note? {
        TODO("Not yet implemented")
    }

    override suspend fun insertNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

}