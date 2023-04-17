package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.adapter.HistoryDetailsAdapter;
import com.example.kdttoeic.model.History;
import com.example.kdttoeic.model.HistoryDetails;
import com.example.kdttoeic.model.Question;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HistoryDetailsActivity extends AppCompatActivity implements HistoryDetailsAdapter.Listener {

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

        historyDetailsAdapter = new HistoryDetailsAdapter(lstAnswer, HistoryDetailsActivity.this);
        rvHistoryDetails.setLayoutManager(new LinearLayoutManager(HistoryDetailsActivity.this,LinearLayoutManager.VERTICAL, false));
        rvHistoryDetails.setAdapter(historyDetailsAdapter);
        rvHistoryDetails.addItemDecoration(new DividerItemDecoration(HistoryDetailsActivity.this,DividerItemDecoration.VERTICAL));

        historyDetailsAdapter.notifyDataSetChanged();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void LoadData(int id) {
        lstAnswer = kdtToeicDB.getAnswerList(id);
    }

    @Override
    public void onItemClick(HistoryDetails historyDetails) {
        Intent intent = new Intent(HistoryDetailsActivity.this, DetailsAnswerActivity.class);
        intent.putExtra("ID_Question", historyDetails.getIdQuestion());
        intent.putExtra("selectOptionUser", historyDetails.getSelectedOptionUser());
        intent.putExtra("correctAnswer", historyDetails.getCorrectAnswer());
        startActivity(intent);
    }
}