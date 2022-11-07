package com.example.noteappcompose.feature_note.data.mapper

import com.example.noteappcompose.feature_note.data.models.NoteDbModel
import com.example.noteappcompose.feature_note.domain.entity.Note

class NoteMapper {

    fun mapEntityToDbModel(note: Note) = NoteDbModel (
        id = note.id,
        title = note.title,
        content = note.content,
        timestamp = note.timestamp,
        color = note.color
    )

    fun mapDbModelToEntity(noteDbModel: NoteDbModel) = Note(
        id = noteDbModel.id,
        title = noteDbModel.title,
        content = noteDbModel.content,
        timestamp = noteDbModel.timestamp,
        color = noteDbModel.color
    )

}