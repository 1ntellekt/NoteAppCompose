package com.example.noteappcompose.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteappcompose.feature_note.data.data_sourse.RoomDB
import com.example.noteappcompose.feature_note.data.mapper.NoteMapper
import com.example.noteappcompose.feature_note.data.repository.NoteRepositoryImpl
import com.example.noteappcompose.feature_note.domain.repository.NoteRepository
import com.example.noteappcompose.feature_note.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(app:Application):RoomDB {
        return Room.databaseBuilder(
            app,RoomDB::class.java,RoomDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMapper():NoteMapper{
        return NoteMapper()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:RoomDB,noteMapper: NoteMapper):NoteRepository{
        return NoteRepositoryImpl(db.noteDao,noteMapper)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
    }

}