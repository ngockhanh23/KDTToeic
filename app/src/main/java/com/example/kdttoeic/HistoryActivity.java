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
import com.example.kdttoeic.adapter.HistoryDetailsAdapter;
import com.example.kdttoeic.model.History;
import com.example.kdttoeic.model.HistoryDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import fragment.SettingFragment;

public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.Listener {
    RecyclerView rvHistory;
    ArrayList<History> lstHistory;
    HistoryAdapter historyAdapter;
    KDTToeicDB kdtToeicDB;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        kdtToeicDB = new KDTToeicDB(HistoryActivity.this);
        db = FirebaseFirestore.getInstance();

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
            case R.id.mnBackupDataHistory:
                optionBackupDataHistory();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void optionBackupDataHistory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("Phục hồi dữ liệu");
        builder.setMessage("Dữ liệu bài làm cũ sẽ được phục hồi, bạn có muốn tiếp tục?");
        builder.setPositiveButton("Phục hồi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               backupDataHistory();
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

    private void backupDataHistory() {
        clearAllHistory();
        db.collection("Thi thu")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot document : task.getResult()) {
//                            int Id = Integer.parseInt(document.getId());
                            int idHis = Integer.parseInt(document.get("id").toString());
                            String topic = document.get("topic").toString();
                            int amountQuestion = Integer.parseInt(document.get("amountQuestion").toString());
                            int maxAmountQuestion = Integer.parseInt(document.get("maxAmountQuestion").toString());
                            float score = Float.parseFloat(document.get("score").toString());

                            kdtToeicDB.insertHistory(topic, amountQuestion, maxAmountQuestion, score);
                            History lastHistory = kdtToeicDB.getHistory().get(kdtToeicDB.getHistory().size()-1);


                            db.collection("Chi tiet bai thi")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @SuppressLint("NotifyDataSetChanged")

                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                                int id = Integer.parseInt(document.get("id").toString());
                                                int idHistoryFB = Integer.parseInt(document.get("idHistory").toString());

                                                if (idHis == idHistoryFB) {
                                                    int idHistory = lastHistory.getId();
                                                    int idQuestion = Integer.parseInt(document.get("idQuestion").toString());
                                                    int selectedOptionUser = Integer.parseInt(document.get("selectedOptionUser").toString());
                                                    int correctAnswer = Integer.parseInt(document.get("correctAnswer").toString());
                                                    kdtToeicDB.insertHistoryDetails(idHistory, selectedOptionUser, correctAnswer, idQuestion);
                                                }
                                            }

                                        }
                                    });





                        }
                        lstHistory.clear();
                        lstHistory.addAll(kdtToeicDB.getHistory());
                        historyAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HistoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

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
        historyAdapter.notifyDataSetChanged();
    }
}