package com.barryzea.mynote.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.barryzea.mynote.Interfaces.NoteDao;
import com.barryzea.mynote.Model.NoteEntity;
import com.barryzea.mynote.db.RoomDatabaseNote;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> allFavoriteNotes;
    public NoteRepository (Application application){
        RoomDatabaseNote db=RoomDatabaseNote.getDataBase(application);
        noteDao=db.noteDao();
        allNotes=noteDao.getAllNotes();
        allFavoriteNotes=noteDao.getFavoritesNotes();

    }
    public LiveData<List<NoteEntity>>getAllNotes(){return  allNotes;}
    public LiveData<List<NoteEntity>>getAllFavorites(){return  allFavoriteNotes;}
    public void insertNote(NoteEntity note){
        new insertAsyncTask(noteDao).execute(note);
    }
    public void updateNote(NoteEntity note){
        new updateAsyncTask(noteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoAsyncTask;

        insertAsyncTask(NoteDao noteDao){
            noteDaoAsyncTask=noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDaoAsyncTask.insertNote(noteEntities[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<NoteEntity, Void,Void>{
        private NoteDao noteDaoAsyncTask;

        updateAsyncTask(NoteDao noteDao){
            noteDaoAsyncTask=noteDao;
        }
        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDaoAsyncTask.updateNote(noteEntities[0]);
            return null;
        }
    }


}
