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
        int level = intent.getIntExtra("level",1);



        switch (level){
            case 1 : {
              tvEnglish.setText("300 - 500");
              tvTiengViet.setText("Dễ");
              break;
            }
            case 2 : {
                tvEnglish.setText("500 - 700");
                tvTiengViet.setText("Trung bình");
                break;
            }
            case 3 : {
                tvEnglish.setText("700 - 900");
                tvTiengViet.setText("Khó");
                break;
            }
            default:
                tvEnglish.setText("300 - 500"); break;

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bắt đầu làm bài
        btStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdtToeicDB.insertHistory("Test".trim(), 0, 0,0 );
                Intent iOpenExam = new Intent(TestDesPageActivity.this, TestActivity.class);

                iOpenExam.putExtra("levelTest", level );

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