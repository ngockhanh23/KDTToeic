package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.example.kdttoeic.adapter.WordAdapter;
import com.example.kdttoeic.model.Word;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements WordAdapter.Listener {

    androidx.appcompat.widget.Toolbar toolbar;
    SearchView searchView;
    ArrayList<Word> arrayList;
    WordAdapter wordAdapter;
    RecyclerView rvSearch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //thanh tìm kiem
        toolbar = findViewById(R.id.toolbarSearch);
        //setSupportActionBar(toolbar);
        searchView = toolbar.findViewById(R.id.searchView);
        rvSearch = findViewById(R.id.rvSearch);
        arrayList = new ArrayList<>();

        Word word = new Word(1, "Dick", "cu");
        arrayList.add(word);
        word = new Word(1, "Cow", "con bò");
        arrayList.add(word);
        word = new Word(1, "Sheep", "cừu");
        arrayList.add(word);
        word = new Word(4, "chick", "cừu");
        arrayList.add(word);
        word = new Word(4, "table", "cừu");
        arrayList.add(word);


        wordAdapter = new WordAdapter(arrayList, this);
        rvSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rvSearch.addItemDecoration( new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvSearch.setAdapter(wordAdapter);

        setVisible(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               wordAdapter.getFilter().filter(newText);
               if(wordAdapter.wordFilter.size() == 0||newText.isEmpty()){
                   setVisible(false);
               }
               else{
                   setVisible(true);
               }
                return false;
            }
        });
    }
    public void setVisible(boolean flag) {
        if (!flag) {
            rvSearch.setVisibility(View.GONE);
        } else {
            rvSearch.setVisibility(View.VISIBLE);
        }
    }
}