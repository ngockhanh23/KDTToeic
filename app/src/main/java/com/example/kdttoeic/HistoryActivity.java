package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
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
import com.example.kdttoeic.model.HistoryDetailsList;
import com.example.kdttoeic.model.HistoryList;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import fragment.SettingFragment;

public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.Listener {
    RecyclerView rvHistory;
    ArrayList<History> lstHistory;
    HistoryAdapter historyAdapter;
    KDTToeicDB kdtToeicDB;
    FirebaseFirestore db;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        kdtToeicDB = new KDTToeicDB(HistoryActivity.this);
        db = FirebaseFirestore.getInstance();

        LoadData();

        historyAdapter = new HistoryAdapter(HistoryActivity.this, lstHistory);

        rvHistory.setLayoutManager(new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, true));
        rvHistory.setAdapter(historyAdapter);

        rvHistory.addItemDecoration(new DividerItemDecoration(HistoryActivity.this, DividerItemDecoration.VERTICAL));

        getSupportActionBar().setTitle("Lịch sử");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateDataToStorage() throws FileNotFoundException {
        Gson gson = new Gson();
        String historyData = gson.toJson(kdtToeicDB.getHistory());
        String historyDetailData = gson.toJson(kdtToeicDB.getAnswerList());

        File historyFile = new File(getFilesDir(), "history.json");
        File historyDetailFile = new File(getFilesDir(), "historyDetail.json");
        String path1 = historyFile.getPath();
        String path2 = historyDetailFile.getPath();
        try {
            FileOutputStream fos = new FileOutputStream(path1);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            bufferedWriter.write(historyData);

            bufferedWriter.close();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(path2);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            bufferedWriter.write(historyDetailData);

            bufferedWriter.close();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference jsonHistoryRef = storageRef.child(path1);
        StorageReference jsonHistoryDetailRef = storageRef.child(path2);
        InputStream stream1 = new FileInputStream(historyFile);
        InputStream stream2 = new FileInputStream(historyDetailFile);

        UploadTask uploadTask1 = jsonHistoryRef.putStream(stream1);
        UploadTask uploadTask2 = jsonHistoryDetailRef.putStream(stream2);

        uploadTask2.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HistoryActivity.this, "Upload failure", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(HistoryActivity.this, "Upload success", Toast.LENGTH_SHORT).show();
            }
        });
        uploadTask1.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });

    }

    private void updateDataFromJson() throws IOException {
//        Keo json file tu firebase storage ve project
        File historyJson = new File(getFilesDir(), "history.json");
        File historyDetailJson = new File(getFilesDir(), "historyDetail.json");

        String path1 = historyJson.getPath();
        String path2 = historyDetailJson.getPath();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference jsonHistoryRef = storageRef.child(path1);
        StorageReference jsonHistoryDetailRef = storageRef.child(path2);

        final long ONE_MEGABYTE = 1024 * 1024;
        jsonHistoryRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

            }
        });
        jsonHistoryDetailRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                Toast.makeText(HistoryActivity.this, "Pull data success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HistoryActivity.this, "Pull data failure", Toast.LENGTH_SHORT).show();
            }
        });

//        Đổ dữ liệu vào lịch sử bài làm
        try {
            InputStream stream1 = new FileInputStream(historyJson);
            InputStream stream2 = new FileInputStream(historyDetailJson);

            String jsonHistoryString = "{\"history\": " + convertInputStreamToString(stream1) + " }";
            String jsonHistoryDetailString = "{\"historyDetails\": " + convertInputStreamToString(stream2) + " }";

            Gson gson = new GsonBuilder().create();
//            Toast.makeText(HistoryActivity.this, "" + jsonString, Toast.LENGTH_LONG).show();
            HistoryList historyList = gson.fromJson(jsonHistoryString, HistoryList.class);
            HistoryDetailsList historyDetailsList = gson.fromJson(jsonHistoryDetailString, HistoryDetailsList.class);

            for (int i = 0; i < historyList.getHistory().size(); i++) {
                String topic = historyList.getHistory().get(i).getTopic();
                int idHis = historyList.getHistory().get(i).getId();
                int amountQuestion = historyList.getHistory().get(i).getAmountQuestion();
                int maxAmountQuestion = historyList.getHistory().get(i).getMaxAmountQuestion();
                float Score = historyList.getHistory().get(i).getScore();
                kdtToeicDB.insertHistory(topic, amountQuestion, maxAmountQuestion, Score);

                int idLastHistory = kdtToeicDB.lastHistory().getId();

                for (int j = 0; j < historyDetailsList.getHistoryDetails().size(); j++) {
                    int idHistoryDetail = historyDetailsList.getHistoryDetails().get(j).getIdHistory();
//
                    if (idHistoryDetail == idHis) {
                        int idHistory = idLastHistory;
                        int selectedOption = historyDetailsList.getHistoryDetails().get(j).getSelectedOptionUser();
                        int correctAnswer = historyDetailsList.getHistoryDetails().get(j).getCorrectAnswer();
                        int idQuestion = historyDetailsList.getHistoryDetails().get(j).getIdQuestion();
                        kdtToeicDB.insertHistoryDetails(idHistory, selectedOption, correctAnswer, idQuestion);
                    }
                }
            }

            lstHistory.clear();
            lstHistory.addAll(kdtToeicDB.getHistory());
            historyAdapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String convertInputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int ch; (ch = is.read()) != -1; ) {
            sb.append((char) ch);
        }
        return sb.toString();
    }

    void LoadData() {
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.mnDeleteItemHistory:
                optionDeleteItemHistory();
                break;
            case R.id.mnClearAllHistory:
                optionClearAllHistory();
                break;
            case R.id.mnBackupDataHistory:
                optionBackupDataHistory();
                break;
            case R.id.mnRestoreDataHistory:
                optionRestoreDataHistory();
        }
        return super.onOptionsItemSelected(item);
    }

    void optionBackupDataHistory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("Sao lưu");
        builder.setMessage("Dữ liệu sẽ được sao lưu trên hệ thống, bạn có muốn tiếp tục?");
        builder.setPositiveButton("Sao lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    updateDataToStorage();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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

    void optionRestoreDataHistory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("Phục hồi");
        builder.setMessage("Dữ liệu sẽ được phục hồi trên máy, bạn có muốn tiếp tục?");
        builder.setPositiveButton("Phục hồi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    updateDataFromJson();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    void optionClearAllHistory() {
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

    void optionDeleteItemHistory() {
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

        for (int i = 0; i < rvHistory.getChildCount();i++) {
            //Lấy trạng thái checkbox tại view của recycle view để kiểm tra
            View view = rvHistory.getChildAt(i);
            CheckBox cb = view.findViewById(R.id.cbHistoryItem);
            if (cb.isChecked()) {
                kdtToeicDB.deleteHistoryDetails(lstHistory.get(i).getId());
                kdtToeicDB.deleteHistory(lstHistory.get(i).getId());
                cb.setChecked(false);
            }

        }
        lstHistory.clear();
        lstHistory.addAll(kdtToeicDB.getHistory());
        historyAdapter.notifyDataSetChanged();
    }

    private void clearAllHistory() {
        kdtToeicDB.clearHistoryDetails();
        kdtToeicDB.clearHistory();
        lstHistory.clear();
        historyAdapter.notifyDataSetChanged();
    }
}

//    private void backupDataHistory() {
//        clearAllHistory();
//        db.collection("Thi thu")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for(QueryDocumentSnapshot document : task.getResult()) {
////                            int Id = Integer.parseInt(document.getId());
//                            int idHis = Integer.parseInt(document.get("id").toString());
//                            String topic = document.get("topic").toString();
//                            int amountQuestion = Integer.parseInt(document.get("amountQuestion").toString());
//                            int maxAmountQuestion = Integer.parseInt(document.get("maxAmountQuestion").toString());
//                            float score = Float.parseFloat(document.get("score").toString());
//
//                            kdtToeicDB.insertHistory(topic, amountQuestion, maxAmountQuestion, score);
//                            History lastHistory = kdtToeicDB.getHistory().get(kdtToeicDB.getHistory().size()-1);
//
//
//                            db.collection("Chi tiet bai thi")
//                                    .get()
//                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                        @SuppressLint("NotifyDataSetChanged")
//
//                                        @Override
//                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                            for (QueryDocumentSnapshot document : task.getResult()) {
////                                                int id = Integer.parseInt(document.get("id").toString());
//                                                int idHistoryFB = Integer.parseInt(document.get("idHistory").toString());
//
//                                                if (idHis == idHistoryFB) {
//                                                    int idHistory = lastHistory.getId();
//                                                    int idQuestion = Integer.parseInt(document.get("idQuestion").toString());
//                                                    int selectedOptionUser = Integer.parseInt(document.get("selectedOptionUser").toString());
//                                                    int correctAnswer = Integer.parseInt(document.get("correctAnswer").toString());
//                                                    kdtToeicDB.insertHistoryDetails(idHistory, selectedOptionUser, correctAnswer, idQuestion);
//                                                }
//                                            }
//
//                                        }
//                                    });
//                        }

//                        lstHistory.clear();
//                        lstHistory.addAll(kdtToeicDB.getHistory());
//                        historyAdapter.notifyDataSetChanged();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(HistoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }

