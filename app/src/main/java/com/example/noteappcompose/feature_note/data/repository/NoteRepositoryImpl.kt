package com.example.noteappcompose.feature_note.data.repository

import com.example.noteappcompose.feature_note.data.data_sourse.NoteDao
import com.example.noteappcompose.feature_note.data.mapper.NoteMapper
import com.example.noteappcompose.feature_note.data.models.NoteDbModel
import com.example.noteappcompose.feature_note.domain.entity.Note
import com.example.noteappcompose.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.*

class NoteRepositoryImpl(
    private val dao: NoteDao,
    private val noteMapper: NoteMapper
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { list->
            list.map { noteDbModel ->
                noteMapper.mapDbModelToEntity(noteDbModel)
            }
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.let { noteMapper.mapDbModelToEntity(it) }
    }

    override suspend fun insertNote(note: Note) {
        dao.addNote(noteMapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(noteMapper.mapEntityToDbModel(note))
    }

}