package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.adapter.HistoryAdapter;
import com.example.kdttoeic.model.History;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.Listener {
    RecyclerView rvHistory;
    ArrayList<History> lstHistory;
    HistoryAdapter historyAdapter;
    KDTToeicDB kdtToeicDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        kdtToeicDB = new KDTToeicDB(HistoryActivity.this);
        LoadData();


        historyAdapter = new HistoryAdapter(HistoryActivity.this,lstHistory);

        rvHistory.setLayoutManager(new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, true));
        rvHistory.setAdapter(historyAdapter);

        rvHistory.addItemDecoration(new DividerItemDecoration(HistoryActivity.this,DividerItemDecoration.VERTICAL));




        getSupportActionBar().setTitle("Lịch sử");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void LoadData(){
        lstHistory = kdtToeicDB.getHistory();
//        lstHistory.clear();
//        lstHistory.addAll(kdtToeicDB.getHistory());
//        lstHistory.add(new History(1,"Luyện tập", 12, 20, 5.4f));
//        lstHistory.add(new History(2,"Luyện tập", 10, 20, 5.0f));
//        lstHistory.add(new History(3,"Thi thử", 15, 20, 7.4f));
    }

    @Override
    public void onItemClick(History history) {
        Intent viewAnswer = new Intent(HistoryActivity.this, HistoryDetailsActivity.class);
        viewAnswer.putExtra("ID_HISTORY", history.getId());
        startActivity(viewAnswer);
//        Toast.makeText(HistoryActivity.this, history.getTopic(), Toast.LENGTH_LONG).show();
    }
}