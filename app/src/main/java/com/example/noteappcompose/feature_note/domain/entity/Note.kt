package com.example.noteappcompose.feature_note.domain.entity

import com.example.noteappcompose.ui.theme.*

data class Note(
    val id:Int,
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int
) {
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
