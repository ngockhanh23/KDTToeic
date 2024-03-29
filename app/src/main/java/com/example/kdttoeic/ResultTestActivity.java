package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.model.History;

public class ResultTestActivity extends AppCompatActivity {

    TextView tvCorrectAnswer, tvMaxAmountQuestion, tvCore, tvCommentResult;
    Button btHomeBack, btViewAnswer;
    KDTToeicDB kdtToeicDB;
    float score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        kdtToeicDB = new KDTToeicDB(ResultTestActivity.this);
        AnhXa();
        Bundle bundle = getIntent().getExtras();
        kdtToeicDB = new KDTToeicDB(ResultTestActivity.this);

        int countCorrectAnswer = bundle.getInt("countCorrectAnswer");
        int maxAmountQuestion = bundle.getInt("maxAmountQuestion");

        History lastHistory = kdtToeicDB.getHistory().get(kdtToeicDB.countHistory()-1);



        score =  (float) countCorrectAnswer / (float) maxAmountQuestion * 100;

        tvCorrectAnswer.setText(String.valueOf(countCorrectAnswer));
        tvMaxAmountQuestion.setText(String.valueOf(maxAmountQuestion));
        tvCore.setText(String.valueOf(Math.floor(score)));

        if(score < 50 ){
            tvCommentResult.setText("Dở, cần học thêm nhiều");
        }
        else {
            tvCommentResult.setText("Chúc mừng bạn đã hoàn thành bài làm");
        }

        btHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btViewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultTestActivity.this, HistoryDetailsActivity.class);
                intent.putExtra("ID_HISTORY", lastHistory.getId());
                startActivity(intent);
            }
        });

    }

    void AnhXa(){
        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        tvMaxAmountQuestion = findViewById(R.id.tvMaxAmountQuestion);
        tvCommentResult = findViewById(R.id.tvCommentResult);
        tvCore = findViewById(R.id.tvCore);
        btHomeBack = findViewById(R.id.btHomeBack);
        btViewAnswer = findViewById(R.id.btViewAnswer);
    }
}