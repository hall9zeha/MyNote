package com.barryzea.mynote.ui;

import android.content.Context;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.barryzea.mynote.Interfaces.ActionsClickUpdate;
import com.barryzea.mynote.Model.NoteEntity;
import com.barryzea.mynote.R;
import com.barryzea.mynote.ViewModel.NewNoteDialogViewModel;


import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class noteFragment extends Fragment  {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<NoteEntity> listNoteEntities;
    private NewNoteDialogViewModel mNoteViewModel;
    private MynoteRecyclerViewAdapter myAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public noteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static noteFragment newInstance(int columnCount) {
        noteFragment fragment = new noteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        listNoteEntities =new ArrayList<>();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (numColumns() <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numColumns(), StaggeredGridLayoutManager.VERTICAL));
            }
            myAdapter = new MynoteRecyclerViewAdapter(listNoteEntities, getActivity(), new ActionsClickUpdate() {
                @Override
                public void onClick(NoteEntity noteEntity) {
                    //showDialogUpdateNote(noteEntity);
                }
            });
            recyclerView.setAdapter(myAdapter);
        }
        setListNotesOfViewModel();
        return view;
    }

    private void setListNotesOfViewModel(){

            mNoteViewModel= new ViewModelProvider(getActivity()).get(NewNoteDialogViewModel.class);
            mNoteViewModel.getAllNotes().observe(getActivity(), new Observer<List<NoteEntity>>() {
                @Override
                public void onChanged(List<NoteEntity> noteEntities) {
                    listNoteEntities.clear();
                    listNoteEntities.addAll(noteEntities);
                    //myAdapter.setNewNotesOfViewModel(noteEntities);
                    myAdapter.notifyDataSetChanged();

                }
            });
    }
    private int numColumns(){
        DisplayMetrics displayMetrics=getContext().getResources().getDisplayMetrics();
        float dpWidth=displayMetrics.widthPixels / displayMetrics.density;
        return (int) dpWidth / 180;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_note,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.actionNewNote:
                showDialogNewNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void showDialogNewNote(){
        FragmentManager fm=getActivity().getSupportFragmentManager();
        NewNoteDialogFragment dialogFragment=new NewNoteDialogFragment();
        dialogFragment.show(fm,"NewDialogAddNote");
    }
    /*
    private void showDialogUpdateNote(NoteEntity note){
       FragmentManager fm=getActivity().getSupportFragmentManager();
        DialogFragmentUpdateNote dialogFragment=new  DialogFragmentUpdateNote();
        DialogFragmentUpdateNote.newInstance(note);
        dialogFragment.show(fm,"UpdateNoteDialog");


    }

     */

}