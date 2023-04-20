package com.example.kdttoeic.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kdttoeic.R;
import com.example.kdttoeic.TestDesPageActivity;
import com.example.kdttoeic.model.Test;

import java.util.List;

import fragment.TestFragment;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    private List<Test> myTest;
    public Listener listener;
    public TestAdapter(List<Test> myTest, Listener listener) {
        this.listener = listener;
        this.myTest = myTest;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_test, parent, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Test test = myTest.get(position);
        if(test == null) {
            return;
        }

        holder.testTitle.setText(test.getTitle());
        holder.testDes.setText(test.getDescription());
        holder.testBtn.setOnClickListener(v -> listener.OnOpenExam(test.getMucde()));
    }

    @Override
    public int getItemCount() {
        return myTest.size();
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView testTitle;
        private TextView testDes;
        private Button testBtn;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            testTitle = itemView.findViewById(R.id.testTitle);
            testDes = itemView.findViewById(R.id.testDes);
            testBtn = itemView.findViewById(R.id.testBtn);
        }
    }

    public interface Listener{
        void OnOpenExam(String mucde);
    }
}
