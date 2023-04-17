package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.kdttoeic.Data.KDTToeicDB;

import java.util.ArrayList;

public class TestDesPageActivity extends AppCompatActivity {
    TextView tvEnglish, tvTiengViet;

    Button btStartExam;
    KDTToeicDB kdtToeicDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prac_fill_sentence);

        tvEnglish = findViewById(R.id.tvEnglish);
        tvTiengViet = findViewById(R.id.tvTiengViet);
        btStartExam = findViewById(R.id.btStartExam);
        kdtToeicDB = new KDTToeicDB(TestDesPageActivity.this);
        Intent intent = getIntent();
        String mucde = intent.getStringExtra("mucde");
        tvEnglish.setText(mucde);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bắt đầu làm bài
        btStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdtToeicDB.insertHistory("Thi thử".trim(), 0, 5,0 );
                Intent iOpenExam = new Intent(TestDesPageActivity.this, TestActivity.class);
                startActivity(iOpenExam);
                finish();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}