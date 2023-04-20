package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TextSizeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String filename = "config";

    TextView tvQuestionPractice, tvAmountQuestionTest, tvMaxAmountQuestionTest;
    RadioGroup rgOptionsQuestion;
    RadioButton rbOpA, rbOpB, rbOpC, rbOpD;
    Button btIncrease, btDecrease, btSave;
    int textsize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_size);

        AnhXa();

        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        textsize = sharedPreferences.getInt("textsize", 16);
        changeTextSize(textsize);

        btIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textsize < 26) {
                    textsize += 2;
                    changeTextSize(textsize);
                } else {
                    Toast.makeText(TextSizeActivity.this, "Bạn đã tăng đến mức tối đa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textsize > 16) {
                    textsize -= 2;
                    changeTextSize(textsize);
                } else {
                    Toast.makeText(TextSizeActivity.this, "Bạn đã giảm đến mức tối thiểu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("textsize", textsize);
                editor.commit();
                finish();
            }
        });
    }

    void changeTextSize(int textsize) {
        tvQuestionPractice.setTextSize(textsize);
        rbOpA.setTextSize(textsize);
        rbOpB.setTextSize(textsize);
        rbOpC.setTextSize(textsize);
        rbOpD.setTextSize(textsize);
    }

    private void AnhXa() {
        tvQuestionPractice = findViewById(R.id.tvQuestionPractice);
        rgOptionsQuestion = findViewById(R.id.rgOptionsQuestion);
        rbOpA = findViewById(R.id.rbOpA);
        rbOpB = findViewById(R.id.rbOpB);
        rbOpC = findViewById(R.id.rbOpC);
        rbOpD = findViewById(R.id.rbOpD);
        btDecrease = findViewById(R.id.btDecrease);
        btIncrease = findViewById(R.id.btIncrease);
        btSave = findViewById(R.id.btSave);
    }
}