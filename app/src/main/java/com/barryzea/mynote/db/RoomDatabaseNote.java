package com.barryzea.mynote.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.barryzea.mynote.Interfaces.NoteDao;
import com.barryzea.mynote.Model.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class RoomDatabaseNote  extends RoomDatabase {

    public abstract NoteDao noteDao();
    private static volatile RoomDatabaseNote INSTANCE;

    public static RoomDatabaseNote getDataBase(final Context context){
        if(INSTANCE==null){
            synchronized (RoomDatabaseNote.class) {
                if (INSTANCE == null) {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),RoomDatabaseNote.class, "noteDb")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
