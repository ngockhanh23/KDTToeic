package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.adapter.HistoryDetailsAdapter;
import com.example.kdttoeic.model.HistoryDetails;

import java.util.ArrayList;

public class HistoryDetailsActivity extends AppCompatActivity {

    RecyclerView rvHistoryDetails;
    ArrayList<HistoryDetails> lstAnswer;
    HistoryDetailsAdapter historyDetailsAdapter;
    KDTToeicDB kdtToeicDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        rvHistoryDetails = findViewById(R.id.rvHistoryDetails);
        kdtToeicDB = new KDTToeicDB(HistoryDetailsActivity.this);
        Bundle bundle = getIntent().getExtras();

        LoadData(bundle.getInt("ID_HISTORY"));

        historyDetailsAdapter = new HistoryDetailsAdapter(lstAnswer);
        rvHistoryDetails.setLayoutManager(new LinearLayoutManager(HistoryDetailsActivity.this,LinearLayoutManager.VERTICAL, false));
        rvHistoryDetails.setAdapter(historyDetailsAdapter);
        rvHistoryDetails.addItemDecoration(new DividerItemDecoration(HistoryDetailsActivity.this,DividerItemDecoration.VERTICAL));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void LoadData(int id){

        lstAnswer = kdtToeicDB.getAnswerList(id);
    }
}