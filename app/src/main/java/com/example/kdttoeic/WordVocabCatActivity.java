package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.adapter.WordAdapter;
import com.example.kdttoeic.model.Word;

import java.util.ArrayList;

public class WordVocabCatActivity extends AppCompatActivity implements WordAdapter.Listener{

    RecyclerView rvWordVocabCats;
    ArrayList<Word> words;
    WordAdapter wordAdapter;

    KDTToeicDB kdtToeicDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_vocab_cat);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        kdtToeicDB = new KDTToeicDB(this);
        words = kdtToeicDB.getVocabCat(id);
        rvWordVocabCats = findViewById(R.id.rvWordVocabCats);

        wordAdapter = new WordAdapter(words,this);
        rvWordVocabCats.setAdapter(wordAdapter);
        rvWordVocabCats.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvWordVocabCats.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    public void OnWordClick(Word word) {
        Intent iOpenDetailWord = new Intent(WordVocabCatActivity.this, WordDetailActivitty.class);
        iOpenDetailWord.putExtra("word", word);
        startActivity(iOpenDetailWord);
    }
}