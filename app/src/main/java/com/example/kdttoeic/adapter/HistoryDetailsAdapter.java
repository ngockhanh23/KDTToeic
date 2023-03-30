package com.example.kdttoeic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kdttoeic.R;
import com.example.kdttoeic.model.HistoryDetails;

import java.util.ArrayList;

public class HistoryDetailsAdapter extends RecyclerView.Adapter<HistoryDetailsAdapter.HistoryDetailsVH> {


    public HistoryDetailsAdapter(ArrayList<HistoryDetails> lstAnswer) {
        this.lstAnswer = lstAnswer;
    }

    ArrayList<HistoryDetails> lstAnswer;
    @NonNull
    @Override
    public HistoryDetailsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_details_item, parent,false);
        return new HistoryDetailsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryDetailsVH holder, int position) {
        HistoryDetails item = lstAnswer.get(position);
        holder.tvIndexAnswer.setText(String.valueOf(position + 1));

        if(item.getSelectedOptionUser() == item.getcorrectAnswer() ){
            holder.ivTickOrCross.setImageResource(R.drawable.correct);
        }

        switch (item.getSelectedOptionUser()){
            case 1: holder.ivAnswerA.setImageResource(R.drawable.a_wrong_answer); break;
            case 2: holder.ivAnswerB.setImageResource(R.drawable.b_wrong_answer); break;
            case 3: holder.ivAnswerC.setImageResource(R.drawable.c_wrong_answer); break;
            case 4: holder.ivAnswerD.setImageResource(R.drawable.d_wrong_answer); break;
        }

        switch (item.getcorrectAnswer()){
            case 1: holder.ivAnswerA.setImageResource(R.drawable.a_correct_answer); break;
            case 2: holder.ivAnswerB.setImageResource(R.drawable.b_correct_answer); break;
            case 3: holder.ivAnswerC.setImageResource(R.drawable.c_correct_answer); break;
            case 4: holder.ivAnswerD.setImageResource(R.drawable.d_correct_answer); break;
        }



    }

    @Override
    public int getItemCount() {
        return lstAnswer.size();
    }

    class HistoryDetailsVH extends RecyclerView.ViewHolder{

        TextView tvIndexAnswer;
        ImageView ivTickOrCross,ivAnswerA, ivAnswerB, ivAnswerC, ivAnswerD;

        public HistoryDetailsVH(@NonNull View itemView) {
            super(itemView);

            tvIndexAnswer = itemView.findViewById(R.id.tvIndexAnswer);
            ivTickOrCross = itemView.findViewById(R.id.ivTickOrCross);
            ivAnswerA = itemView.findViewById(R.id.ivAnswerA);
            ivAnswerB = itemView.findViewById(R.id.ivAnswerB);
            ivAnswerC = itemView.findViewById(R.id.ivAnswerC);
            ivAnswerD = itemView.findViewById(R.id.ivAnswerD);
        }
    }
}
