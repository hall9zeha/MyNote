package com.barryzea.mynote.ui;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.barryzea.mynote.Interfaces.ActionsClickUpdate;
import com.barryzea.mynote.Model.NoteEntity;
import com.barryzea.mynote.R;
import com.barryzea.mynote.ViewModel.NewNoteDialogViewModel;
import com.barryzea.mynote.databinding.FragmentItemBinding;

import java.util.List;


public class  MynoteRecyclerViewAdapter extends RecyclerView.Adapter<MynoteRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context ctx;
    public NewNoteDialogViewModel viewModel;
    public ActionsClickUpdate listener;


    public MynoteRecyclerViewAdapter(List<NoteEntity> items, Context ctx, ActionsClickUpdate listener) {
        mValues = items;
        this.ctx=ctx;
        this.viewModel=new ViewModelProvider((AppCompatActivity) ctx).get(NewNoteDialogViewModel.class);
        this.listener=listener;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getContent());
        if(holder.mItem.isFavorite()){
            holder.ivFav.setImageResource(R.drawable.ic_star);
        }
        else{
            holder.ivFav.setImageResource(R.drawable.ic_star_empty);
        }
        holder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.mItem.isFavorite()){
                    holder.mItem.setFavorite(false);
                    holder.ivFav.setImageResource(R.drawable.ic_star_empty);
                }
                else{
                    holder.mItem.setFavorite(true);
                    holder.ivFav.setImageResource(R.drawable.ic_star);
                }
                viewModel.updateNote(holder.mItem);
            }

        });
        holder.viewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(holder.mItem);
                showDialog(holder.mItem);

            }
        });

    }

    private void showDialog(NoteEntity note){
        FragmentManager fm=((AppCompatActivity)ctx).getSupportFragmentManager();;
        DialogFragmentUpdateNote dialogFragment=new  DialogFragmentUpdateNote();
        DialogFragmentUpdateNote.newInstance(note, viewModel);
        dialogFragment.show(fm,"UpdateNoteDialog");
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNewNotesOfViewModel(List<NoteEntity> newList){
        this.mValues=newList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public NoteEntity mItem;
        public ImageView ivFav;
        public View viewMain;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.textViewTitle;
            mContentView = binding.textViewContent;
            ivFav=binding.imageViewFavorite;
            viewMain=binding.getRoot();

        }


        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}