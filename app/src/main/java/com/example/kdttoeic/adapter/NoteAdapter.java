package com.example.kdttoeic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kdttoeic.R;
import com.example.kdttoeic.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    ArrayList<Note> notes;
    Listener listener;

    public NoteAdapter(ArrayList<Note> notes, Listener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent, false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, int position) {
        Note note = notes.get(position);

        if (note.getTitle().length() > 10) {
            String title = note.getTitle().substring(0, 10);
            holder.tvTitle.setText(title + "...");
        } else {
            holder.tvTitle.setText(note.getTitle());
        }

        if (note.getContent().length() > 30) {
            String content = note.getContent().substring(0, 30);
            holder.tvContent.setText(content + "...");
        } else {
            holder.tvContent.setText(note.getContent());
        }

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnEditClick(position, note);
            }
        });

        holder.ivDelte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDeleteNote(position, note);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnEditClick(position, note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteVH extends RecyclerView.ViewHolder {

        TextView tvTitle, tvContent;
        ImageView ivEdit, ivDelte;

        public NoteVH(@NonNull View itemView) {
            super(itemView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelte = itemView.findViewById(R.id.ivDelete);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    public interface Listener {
        void OnEditClick(int pos, Note note);

        void OnDeleteNote(int pos, Note note);
    }
}
