package com.example.kdttoeic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kdttoeic.model.VocabCat;
import com.example.kdttoeic.R;

import java.util.ArrayList;


public class VocabCatAdapter extends RecyclerView.Adapter<VocabCatAdapter.VocabCatVH> {

    ArrayList<VocabCat> vocabCats;
    Listener listener;

    public VocabCatAdapter(ArrayList<VocabCat> vocabCats, Listener listener) {
        this.vocabCats = vocabCats;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VocabCatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocab_cat_row, parent, false);

        return new VocabCatVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabCatVH holder, int position) {
        VocabCat vocabCat = vocabCats.get(position);
        holder.tvVocabcat.setText(vocabCat.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick(vocabCat);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vocabCats.size();
    }

    class VocabCatVH extends RecyclerView.ViewHolder {
        TextView tvVocabcat;

        public VocabCatVH(@NonNull View itemView) {
            super(itemView);
            tvVocabcat = itemView.findViewById(R.id.tvVocabcat);
        }
    }

    public interface Listener {
        void OnItemClick(VocabCat vocabCat);
    }
}
