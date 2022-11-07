package com.example.noteappcompose.feature_note.data.data_sourse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappcompose.feature_note.data.models.NoteDbModel

@Database(
    entities = [NoteDbModel::class],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract val noteDao:NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }

}