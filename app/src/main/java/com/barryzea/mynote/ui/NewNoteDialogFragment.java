package com.barryzea.mynote.ui;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.barryzea.mynote.Model.NoteEntity;
import com.barryzea.mynote.R;
import com.barryzea.mynote.ViewModel.NewNoteDialogViewModel;

import org.jetbrains.annotations.NotNull;

public class NewNoteDialogFragment extends DialogFragment {

    private NewNoteDialogViewModel mViewModel;
    private EditText etTitle;
    private EditText etContent;
    private RadioGroup rgButton;
    private Switch swFav;


    private View view;





    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.newNoteTitle);
        builder.setMessage(getString(R.string.hintNewNote))
                .setPositiveButton(R.string.addNote, (dialogInterface, i) -> {

                    String title = etTitle.getText().toString();
                    String content = etContent.getText().toString();
                    String color = "Amarillo";
                    switch (rgButton.getCheckedRadioButtonId()) {
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
                    boolean isFavorite = swFav.isChecked();
                    //comunicando la nueva nota con el view model
                    NewNoteDialogViewModel mViewModel =new  ViewModelProvider(getActivity()).get(NewNoteDialogViewModel.class);
                    mViewModel.insertNote(new NoteEntity(title, content, isFavorite, color));
                    dismiss();
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    dismiss();
                });






        view=inflater.inflate(R.layout.new_note_dialog_fragment, null);
        etTitle= view.findViewById(R.id.editTextTextTitleEdit);
        etContent=view.findViewById(R.id.editTextTextContentEdit);
        rgButton= view.findViewById(R.id.radioGroupEdit);
        swFav=view.findViewById(R.id.switchFavoriteEdit);
        builder.setView(view);
        return builder.create();
    }
}