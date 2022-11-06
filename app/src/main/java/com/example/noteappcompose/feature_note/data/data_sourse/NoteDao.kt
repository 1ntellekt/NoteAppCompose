package com.example.noteappcompose.feature_note.data.data_sourse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteappcompose.feature_note.data.models.NoteDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes():Flow<List<NoteDbModel>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id:Int):NoteDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteDbModel: NoteDbModel)

    @Delete
    suspend fun deleteNote(noteDbModel: NoteDbModel)

}