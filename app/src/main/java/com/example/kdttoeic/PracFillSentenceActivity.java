package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PracFillSentenceActivity extends AppCompatActivity {

    TextView tvEnglish, tvTiengViet;

    Spinner snAmountQuestion;
    Button btStartExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prac_fill_sentence);

        tvEnglish = findViewById(R.id.tvEnglish);
        tvTiengViet = findViewById(R.id.tvTiengViet);
        snAmountQuestion = findViewById(R.id.snAmountQuestion);
        btStartExam = findViewById(R.id.btStartExam);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvEnglish.setText("No pain no gain");
        tvTiengViet.setText("Không đau không trưởng thành");
        //Bắt đầu làm bài
        btStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iOpenExam = new Intent(PracFillSentenceActivity.this, FillSentenceExam.class);
                startActivity(iOpenExam);
            }
        });


        //Xử lí dropdownlist
        ArrayList<String> AmountQuestion = new ArrayList<String>();
        for (int i = 1; i <= 20; i++) {
            AmountQuestion.add(i + "");
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, AmountQuestion);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        snAmountQuestion.setAdapter(arrayAdapter);

        snAmountQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}