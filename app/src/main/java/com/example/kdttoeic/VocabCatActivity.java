package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.kdttoeic.model.VocabCat;
import com.example.kdttoeic.adapter.VocabCatAdapter;

import java.util.ArrayList;

public class VocabCatActivity extends AppCompatActivity implements VocabCatAdapter.Listener {

    RecyclerView rvVocabcats;
    ArrayList<VocabCat> vocabCats;
    VocabCatAdapter vocabCatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_cat);

        rvVocabcats = findViewById(R.id.rvVocabcats);

        vocabCats = new ArrayList<>();
        VocabCat vocabCat = new VocabCat(1, "TRAFFIC");
        vocabCats.add(vocabCat);
        vocabCat = new VocabCat(2, "INFORMATION");
        vocabCats.add(vocabCat);
        vocabCat = new VocabCat(3, "LIFE");
        vocabCats.add(vocabCat);
        vocabCat = new VocabCat(4, "SOCIETY");
        vocabCats.add(vocabCat);

        vocabCatAdapter = new VocabCatAdapter(vocabCats, this);
        rvVocabcats.setAdapter(vocabCatAdapter);
        rvVocabcats.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rvVocabcats.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void OnItemClick(VocabCat vocabCat) {
        Intent i = new Intent(this, WordVocabCatActivity.class);
        i.putExtra("id", vocabCat.getId());
        startActivity(i);
    }
}