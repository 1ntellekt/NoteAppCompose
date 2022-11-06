package com.example.noteappcompose.feature_note.domain.entity

data class Note(
    val id:Int,
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int
)
