package com.barryzea.mynote.Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.barryzea.mynote.Model.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertNote(NoteEntity note);

    @Update
    void updateNote(NoteEntity note);

    @Query("delete from notes")
    void deleteAllNotes();

    @Query("delete from notes where id=:idNote")
    void deleteNoteById(int idNote);

    @Query("SELECT * FROM notes")
    LiveData<List<NoteEntity>> getAllNotes();

    @Query("SELECT * FROM notes WHERE favorite LIKE 'true'")
    LiveData<List<NoteEntity>> getFavoritesNotes();
}
