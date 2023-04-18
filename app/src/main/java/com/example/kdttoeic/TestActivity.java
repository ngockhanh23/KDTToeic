package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.model.History;
import com.example.kdttoeic.model.HistoryDetails;
import com.example.kdttoeic.model.Question;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Collections;

public class TestActivity extends AppCompatActivity {
    TextView tvQuestionDes;
    RadioGroup tesOptionsQuestion;
    RadioButton tesOpA, tesOpB, tesOpC, tesOpD;
    Button btTesNextQuestion;
    CountDownTimer cdt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String filename = "config";
    int textsize;
    FirebaseFirestore db;

    KDTToeicDB kdtToeicDB;

    ArrayList<Question> lstQuestion;
    private int count = 1; //vị trí câu hỏi
    private int countCorrectAnswer = 0;  // số câu đúng
    private int maxAmountQuestion;
    private int correctAnswer ;
    private int optionUser = -1;
    private ArrayList<HistoryDetails> lstHitoryDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView cdTime = findViewById(R.id.cdTime);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        kdtToeicDB = new KDTToeicDB(TestActivity.this);
        db = FirebaseFirestore.getInstance();

        AnhXa();
        AddQuestion();

        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        textsize = sharedPreferences.getInt("textsize", 16);
        changeTextSize(textsize);

        tvQuestionDes.setText(lstQuestion.get(0).getContent());
        tesOpA.setText(lstQuestion.get(0).getOpA());
        tesOpB.setText(lstQuestion.get(0).getOpB());
        tesOpC.setText(lstQuestion.get(0).getOpC());
        tesOpD.setText(lstQuestion.get(0).getOpD());

        correctAnswer = lstQuestion.get(0).getAnswer();

        // Lich su bai thi moi nhat
        History lastHistory = kdtToeicDB.lastHistory();
        lstHitoryDetails = new ArrayList<>();

        tesOptionsQuestion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tesOpA:
                        optionUser = 1;
                        break;
                    case R.id.tesOpB:
                        optionUser = 2;
                        break;
                    case R.id.tesOpC:
                        optionUser = 3;
                        break;
                    case R.id.tesOpD:
                        optionUser = 4;
                        break;
                }
            }
        });

//        Bundle bundle = getIntent().getExtras();
        maxAmountQuestion = 5;


        //xử lý sự kiện khi click sang câu hỏi tiếp theo
        btTesNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionUser == correctAnswer){
                    countCorrectAnswer++;
                }

                if(count == maxAmountQuestion)
                {
                    kdtToeicDB.insertHistoryDetails(lastHistory.getId(), optionUser, correctAnswer, lstQuestion.get(count-1).getId());

//                    HistoryDetails lastHistoryDetails = kdtToeicDB.getAnswerList(lastHistory.getId()).get(kdtToeicDB.countHistoryDetail(lastHistory.getId())-1);
                    HistoryDetails lastHistoryDetails = kdtToeicDB.lastHistoryDetails(lastHistory.getId());
                    lstHitoryDetails.add(lastHistoryDetails);

                    Intent intent = new Intent(TestActivity.this, ResultTestActivity.class);
                    intent.putExtra("countCorrectAnswer", countCorrectAnswer);
                    intent.putExtra("maxAmountQuestion", maxAmountQuestion);

                    float score = (float) countCorrectAnswer / (float) maxAmountQuestion * 100;

                    // Cap nhat du lieu bai lam len lich su bai lam
                    kdtToeicDB.updateHistory(lastHistory.getId(), countCorrectAnswer, maxAmountQuestion, score);

                    // Dua du lieu len firestore
                    db.collection("Thi thu").document("" + lastHistory.getId())
                                    .set(kdtToeicDB.getHistory().get(kdtToeicDB.countHistory() - 1), SetOptions.merge());

                    for(int i = 0; i < lstHitoryDetails.size(); i++)
                    {
                        db.collection("Chi tiet bai thi").add(lstHitoryDetails.get(i));
                    }

                    startActivity(intent);
                    finish();
                }

                else{

                    //thêm lịch sử đáp án
                    if(count == 1){
                        kdtToeicDB.insertHistoryDetails(lastHistory.getId(),optionUser,correctAnswer,lstQuestion.get(0).getId());
                        // Chi tiet lich su bai thi moi nhat
                        HistoryDetails lastHistoryDetails = kdtToeicDB
                                .getAnswerList(lastHistory.getId())
                                .get(kdtToeicDB.countHistoryDetail(lastHistory.getId())-1);

                        lstHitoryDetails.add(lastHistoryDetails);
                    }

                    else {
                        kdtToeicDB.insertHistoryDetails(lastHistory.getId()
                                ,optionUser,correctAnswer,lstQuestion.get(count-1).getId());
                        // Chi tiet lich su bai thi moi nhat
                        HistoryDetails lastHistoryDetails = kdtToeicDB
                                .getAnswerList(lastHistory.getId())
                                .get(kdtToeicDB.countHistoryDetail(lastHistory.getId())-1);

                        lstHitoryDetails.add(lastHistoryDetails);
                    }

                    tesOptionsQuestion.clearCheck();
                    tvQuestionDes.setText(lstQuestion.get(count).getContent());
                    tesOpA.setText(lstQuestion.get(count).getOpA());
                    tesOpB.setText(lstQuestion.get(count).getOpB());
                    tesOpC.setText(lstQuestion.get(count).getOpC());
                    tesOpD.setText(lstQuestion.get(count).getOpD());
                    correctAnswer = lstQuestion.get(count).getAnswer();
                    optionUser = -1;
                    count++;


                }
            }
        });



        cdt = new CountDownTimer(150000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                cdTime.setText("Thoi gian: " + millisUntilFinished / 1000);
                if(count == maxAmountQuestion) {
                    cancel();
                }
            }

            @Override
            public void onFinish() {

                if(optionUser == correctAnswer){
                    countCorrectAnswer++;
                }
                Intent intent = new Intent(TestActivity.this, ResultTestActivity.class);
                intent.putExtra("countCorrectAnswer", countCorrectAnswer);
                intent.putExtra("maxAmountQuestion", maxAmountQuestion);
                startActivity(intent);
                finish();
            }
        }.start();

        getSupportActionBar().setTitle("Luyện tập");
    }

    void changeTextSize(int textsize)
    {
        tvQuestionDes.setTextSize(textsize);
        tesOpA.setTextSize(textsize);
        tesOpB.setTextSize(textsize);
        tesOpC.setTextSize(textsize);
        tesOpD.setTextSize(textsize);
    }

    void AnhXa(){
        tvQuestionDes = findViewById(R.id.tvQuestionDes);
        tesOptionsQuestion = findViewById(R.id.tesOptionsQuestion);
        tesOpA = findViewById(R.id.tesOpA);
        tesOpB = findViewById(R.id.tesOpB);
        tesOpC = findViewById(R.id.tesOpC);
        tesOpD = findViewById(R.id.tesOpD);
        btTesNextQuestion = findViewById(R.id.btTesNextQuestion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mnInflater = getMenuInflater();
        mnInflater.inflate(R.menu.exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnExit){
            ExitButtonOption();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void ExitButtonOption(){
        AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
        builder.setTitle("Oh no !!!");
        builder.setMessage("Nếu bạn thoát, tiến độ làm bài của bạn sẽ không được lưu lại, bạn có chắc chắn muốn thoát hay không ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                History lastHistory = kdtToeicDB.getHistory().get(kdtToeicDB.countHistory()-1);
                kdtToeicDB.deleteHistoryDetails(lastHistory.getId());
                kdtToeicDB.deleteHistory(lastHistory.getId());
                cdt.cancel();
                finish();
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

    void AddQuestion(){
        lstQuestion = kdtToeicDB.getQuestion();
    }
}