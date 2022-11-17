package com.example.noteappcompose.feature_note.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappcompose.ui.theme.*

@Entity(tableName = "note")
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int
)

class InvalidNoteException(message:String):Exception(message)
