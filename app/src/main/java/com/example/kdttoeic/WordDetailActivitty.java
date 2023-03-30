package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kdttoeic.model.Word;

public class WordDetailActivitty extends AppCompatActivity {

    ImageView imDescribe;
    TextView tvEn, tvSpell, tvVe, tvEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail_activitty);

        Intent i = getIntent();
        Word word = (Word) i.getSerializableExtra("word");

        imDescribe = findViewById(R.id.imDescribe);
        tvEn = findViewById(R.id.tvEn);
        tvSpell = findViewById(R.id.tvSpell);
        tvVe = findViewById(R.id.tvVe);
        tvEx = findViewById(R.id.tvEx);


        tvEn.setText(word.getEn());
        tvSpell.setText("/" + word.getSpell() + "/");
        tvVe.setText(word.getVe());
        tvEx.setText(word.getExample());
    }
}