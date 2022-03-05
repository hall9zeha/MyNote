package com.barryzea.mynote.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.barryzea.mynote.Model.NoteEntity;
import com.barryzea.mynote.Repository.NoteRepository;

import java.util.List;

public class DialogFragmentUpdateNoteViewModel extends AndroidViewModel {
    private LiveData<List<NoteEntity>> listNotes;
    private NoteRepository noteRepository;
    public DialogFragmentUpdateNoteViewModel(Application application){
        super(application);

        noteRepository=new NoteRepository(application);
        listNotes=noteRepository.getAllNotes();
    }
    public LiveData<List<NoteEntity>> getAllNotes(){return listNotes;}

    public void updateNote(NoteEntity note){noteRepository.updateNote(note);}
}