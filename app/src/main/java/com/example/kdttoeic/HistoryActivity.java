package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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

    

    void LoadData(){
        lstHistory = kdtToeicDB.getHistory();
//
    }

    @Override
    public void onItemClick(int idHistory) {
        Intent viewAnswer = new Intent(HistoryActivity.this, HistoryDetailsActivity.class);
        viewAnswer.putExtra("ID_HISTORY", idHistory);
        startActivity(viewAnswer);
//        Toast.makeText(HistoryActivity.this, String.valueOf(idHistory), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.history_option_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == android.R.id.home){
//            finish();
//        }
        switch (item.getItemId()){
            case android.R.id.home: finish();
            case R.id.mnDeleteItemHistory:
                optionDeleteItemHistory();
                break;
            case R.id.mnClearAllHistory:
                optionClearAllHistory();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void optionClearAllHistory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("Xóa toàn bộ lịch sử");
        builder.setMessage("Nếu bạn xóa toàn bộ lịch sử, lịch sử làm bài của bạn sẽ bị mất toàn bộ, bạn có chắc chắn muốn xóa hay không ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAllHistory();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.show();
        alertDialog.show();
    }

    void optionDeleteItemHistory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("Xóa lịch sử làm bài");
        builder.setMessage("Bạn có chắc chắn muốn xóa những bài làm đã chọn hay không ???");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItemHistory();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.show();
        alertDialog.show();
    }

    private void deleteItemHistory() {

        for(int i = rvHistory.getChildCount()-1; i>=0;i--){
            //Lấy trạng thái checkbox tại view của recycle view để kiểm tra
            View view = rvHistory.getChildAt(i);
            CheckBox cb = view.findViewById(R.id.cbHistoryItem);
            if(cb.isChecked()){
                kdtToeicDB.deleteHistoryDetails(lstHistory.get(i).getId());
                kdtToeicDB.deleteHistory(lstHistory.get(i).getId());
                cb.setChecked(false);

            }
            lstHistory.clear();
            lstHistory.addAll(kdtToeicDB.getHistory());
        }
        historyAdapter.notifyDataSetChanged();
    }


    private void clearAllHistory() {
        kdtToeicDB.clearHistoryDetails();
        kdtToeicDB.clearHistory();
        lstHistory.clear();
        lstHistory.addAll(kdtToeicDB.getHistory());
        historyAdapter.notifyDataSetChanged();

    }




}