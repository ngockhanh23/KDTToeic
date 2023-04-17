package com.example.kdttoeic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kdttoeic.R;
import com.example.kdttoeic.Utils;
import com.example.kdttoeic.model.History;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    ArrayList<History> lstHistory;
    Listener listener;

    public HistoryAdapter(Listener listener,  ArrayList<History> lstHistory) {
        this.listener =listener;
        this.lstHistory = lstHistory;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History item = lstHistory.get(position);

        if (item.getTopic().equals("Thi thử".trim())){
            holder.ivImageHistoryTopic.setImageResource(R.drawable.test_icon_history);
        }

        holder.ctId.setId(item.getId());
        holder.tvTopicHistory.setText(item.getTopic());
        holder.tvAmountQuestionHistory.setText(String.valueOf(item.getAmountQuestion()));
        holder.tvMaxAmountQuestionHistory.setText(String.valueOf(item.getMaxAmountQuestion()));
        holder.tvScoreHistory.setText(String.valueOf(Math.floor(item.getScore())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstHistory.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ctId;
        TextView tvTopicHistory, tvAmountQuestionHistory, tvMaxAmountQuestionHistory, tvScoreHistory;
        ImageView ivImageHistoryTopic;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            ctId = itemView.findViewById(R.id.ctId);
            tvTopicHistory = itemView.findViewById(R.id.tvTopicHistory);
            tvAmountQuestionHistory = itemView.findViewById(R.id.tvAmountQuestionHistory);
            tvMaxAmountQuestionHistory = itemView.findViewById(R.id.tvMaxAmountQuestionHistory);
            tvScoreHistory = itemView.findViewById(R.id.tvScoreHistory);
            ivImageHistoryTopic = itemView.findViewById(R.id.ivImageHistoryTopic);
        }

    }

    public interface Listener{
        void onItemClick(int idHistory);
    }


}
