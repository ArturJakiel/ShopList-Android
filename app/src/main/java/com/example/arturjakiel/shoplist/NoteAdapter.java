package com.example.arturjakiel.shoplist;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>{
    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;
    private OnCheckBoxListener checkboxeslistner;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_list_item, parent, false);
        return new NoteHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.checkBoxComplete.setChecked(currentNote.getComplete() == 1 ? true : false);
//        holder.checkBoxComplete.setChecked(currentNote.getComplete());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private CheckBox checkBoxComplete;


        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            checkBoxComplete = itemView.findViewById(R.id.checkbox_item_complete);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(position));
                    }
                }
            });

//            //check box
            checkBoxComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (checkboxeslistner != null && position != RecyclerView.NO_POSITION) {
                        checkboxeslistner.onCheckBoxClick(notes.get(position));
                    }
                }
            });
//              this generate bugs
//            checkBoxComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    int position = getAdapterPosition();
//                    if (checkboxeslistner != null && position != RecyclerView.NO_POSITION) {
//                        checkboxeslistner.onCheckBoxClick(notes.get(position),isChecked);
//                    }
//                }
//            });
        }
    }
    //clicking anywhere on item
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    //clicking on CheckBox
    public interface OnCheckBoxListener{
        void onCheckBoxClick(Note note);
    }
    public void setOnCheckBoxListener(OnCheckBoxListener checkboxeslistner){
        this.checkboxeslistner = checkboxeslistner;
    }
}
