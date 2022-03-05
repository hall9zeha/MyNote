package com.barryzea.mynote.ui;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.barryzea.mynote.Model.NoteEntity;
import com.barryzea.mynote.R;
import com.barryzea.mynote.ViewModel.DialogFragmentUpdateNoteViewModel;
import com.barryzea.mynote.ViewModel.NewNoteDialogViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DialogFragmentUpdateNote extends DialogFragment {
    private static NoteEntity noteEntity;
    private EditText etTitleEdit;
    private EditText etContentEdit;
    private RadioGroup rgButtonEdit;
    private Switch swFavEdit;
    private static NewNoteDialogViewModel viewModelNew;

    public View viewEdit;



    public static DialogFragmentUpdateNote newInstance(NoteEntity note, NewNoteDialogViewModel viewModel) {
        noteEntity=note;
        viewModelNew=viewModel;
        return new DialogFragmentUpdateNote();

    }





    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Editar nota");
        builder.setMessage("Modifique los datos")
                .setPositiveButton(R.string.updateNote, (dialogInterface, i) -> {

                    String title = etTitleEdit.getText().toString();
                    String content = etContentEdit.getText().toString();
                    String color = "Amarillo";
                    switch (rgButtonEdit.getCheckedRadioButtonId()) {
                        case R.id.radioButtonVerdeEdit:
                            color = "verde";
                            break;

                        case R.id.radioButtonAmarilloEdit:
                            color = "amarillo";
                            break;

                        case R.id.radioButtonCelesteEdit:
                            color = "celeste";
                            break;


                    }
                    boolean isFavorite = swFavEdit.isChecked();
                    NoteEntity note= new NoteEntity();
                    note.setName(title);
                    note.setContent(content);
                    note.setFavorite(isFavorite);
                    note.setColor(color);
                    note.setId(noteEntity.getId());
                    //comunicando la nueva nota con el view model

                    //NewNoteDialogViewModel mViewModel = new ViewModelProvider(getActivity()).get(NewNoteDialogViewModel.class);
                    //mViewModel.updateNote(note);
                    viewModelNew.updateNote(note);

                    Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();


                    dismiss();
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    dismiss();
                });






        viewEdit=inflater.inflate(R.layout.new_note_dialog_fragment, null);
        etTitleEdit= viewEdit.findViewById(R.id.editTextTextTitleEdit);
        etContentEdit=viewEdit.findViewById(R.id.editTextTextContentEdit);
        rgButtonEdit= viewEdit.findViewById(R.id.radioGroupEdit);
        swFavEdit=viewEdit.findViewById(R.id.switchFavoriteEdit);

        etTitleEdit.setText(noteEntity.getName());
        etContentEdit.setText(noteEntity.getContent());
        switch (noteEntity.getColor()) {
            case "verde":
                rgButtonEdit.check(R.id.radioButtonVerdeEdit);
                break;

            case "amarillo":
                rgButtonEdit.check(R.id.radioButtonAmarilloEdit);
                break;

            case "celeste":
                rgButtonEdit.check(R.id.radioButtonCelesteEdit);
                break;


        }
        swFavEdit.setChecked(noteEntity.isFavorite());


        builder.setView(viewEdit);
        return builder.create();
    }
}