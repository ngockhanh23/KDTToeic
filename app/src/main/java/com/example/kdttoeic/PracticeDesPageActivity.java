package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PracticeDesPageActivity extends AppCompatActivity {
    Button btStartPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_des_page);

        btStartPractice = findViewById(R.id.btStartPractice);
        btStartPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeDesPageActivity.this, PracticeActivity.class);
                startActivity(intent);
            }
        });
    }
}