package com.example.kdttoeic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kdttoeic.R;
import com.example.kdttoeic.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordVH> implements Filterable {

    public ArrayList<Word> words;
    public ArrayList<Word> wordFilter;
    Listener listener;

    public WordAdapter(ArrayList<Word> words, Listener listener) {
        this.words = words;
        this.listener = listener;
        this.wordFilter = words;
    }

    @NonNull
    @Override
    public WordVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_row, parent, false);
        return new WordVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordVH holder, int position) {
        Word word = wordFilter.get(position);
        holder.tvEn.setText(word.getEn());
        holder.tvVe.setText(word.getVe());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnWordClick(word);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new WordFilter();
    }

    class WordFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //Keyword search trên thanh tìm kiếm
            String strSearch = constraint.toString();
            if (strSearch.isEmpty()) {
                //Khi chưa nhập gì thì sẽ hiển thị hết
                wordFilter = words;
            } else {
                List<Word> word1 = new ArrayList<>();
                //Tk nào giống key word đã truyền thì add vào
                for (Word word : words) {
                    if (word.getEn().toLowerCase().contains(strSearch.toLowerCase())) {
                        word1.add(word);
                    }
                }
                wordFilter = (ArrayList<Word>) word1;
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = wordFilter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            wordFilter = (ArrayList<Word>) results.values;
            notifyDataSetChanged();
        }
    }

    class WordVH extends RecyclerView.ViewHolder {
        TextView tvEn, tvVe;

        public WordVH(@NonNull View itemView) {
            super(itemView);
            tvEn = itemView.findViewById(R.id.tvEn);
            tvVe = itemView.findViewById(R.id.tvVe);
        }
    }

    public interface Listener {
        void OnWordClick(Word word);
    }
}
