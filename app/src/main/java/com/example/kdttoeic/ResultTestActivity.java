package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultTestActivity extends AppCompatActivity {

    TextView tvCorrectAnswer, tvMaxAmountQuestion, tvCore, tvCommentResult;
    Button btHomeBack;
    float score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        AnhXa();
        Bundle bundle = getIntent().getExtras();

        int countCorrectAnswer = bundle.getInt("countCorrectAnswer");
        int maxAmountQuestion = bundle.getInt("maxAmountQuestion");


        score =  (float) countCorrectAnswer / (float) maxAmountQuestion * 100;

        tvCorrectAnswer.setText(String.valueOf(countCorrectAnswer));
        tvMaxAmountQuestion.setText(String.valueOf(maxAmountQuestion));
        tvCore.setText(String.valueOf(Math.floor(score)));

        if(score < 50 ){
            tvCommentResult.setText("Như con cặc, cần học thêm nhiều");
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

    }

    void AnhXa(){
        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        tvMaxAmountQuestion = findViewById(R.id.tvMaxAmountQuestion);
        tvCommentResult = findViewById(R.id.tvCommentResult);
        tvCore = findViewById(R.id.tvCore);
        btHomeBack = findViewById(R.id.btHomeBack);
    }
}