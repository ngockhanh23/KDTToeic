package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kdttoeic.Data.KDTToeicDB;

import java.util.ArrayList;


public class PracticeDesPageActivity extends AppCompatActivity {
    Button btStartPractice;
    Spinner spQuestionCount;
    private String AmountQuestion;
    KDTToeicDB kdtToeicDB;


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

        kdtToeicDB = new KDTToeicDB(PracticeDesPageActivity.this);
        AnhXa();

        //Số câu
        ArrayList<String> AmountQuestionOption = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            AmountQuestionOption.add(i + "");
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, AmountQuestionOption);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spQuestionCount.setAdapter(arrayAdapter);

        spQuestionCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AmountQuestion = AmountQuestionOption.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //end-Số câu


        btStartPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thêm lịch sử
                kdtToeicDB.insertHistory("Luyện tập",0,Integer.parseInt(AmountQuestion), 0);

                Intent intent = new Intent(PracticeDesPageActivity.this, PracticeActivity.class);
                intent.putExtra("maxAmountQuestion", AmountQuestion);
                startActivity(intent);
                finish();
            }
        });
        getSupportActionBar().setTitle("Luyện tập");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void AnhXa(){
        btStartPractice = findViewById(R.id.btStartPractice);
        spQuestionCount = findViewById(R.id.spQuestionCount);
    }

}