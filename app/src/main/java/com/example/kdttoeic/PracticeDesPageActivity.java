package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PracticeDesPageActivity extends AppCompatActivity {
    Button btStartPractice;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Thoát trang
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
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
        getSupportActionBar().setTitle("Luyện tập");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}