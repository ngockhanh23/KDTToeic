package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.adapter.WordAdapter;
import com.example.kdttoeic.model.Word;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements WordAdapter.Listener {

    RecyclerView rvSearch;
    ArrayList<Word> arrayList;
    WordAdapter wordAdapter;
    KDTToeicDB kdtToeicDB;
    TextView tvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvSearch = findViewById(R.id.rvSearch);
        arrayList = new ArrayList<>();
        kdtToeicDB = new KDTToeicDB(SearchActivity.this);
        tvSearch = findViewById(R.id.tvSearch);
        arrayList = kdtToeicDB.getVocab();

        wordAdapter = new WordAdapter(arrayList, this);
        rvSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        rvSearch.addItemDecoration(new DividerItemDecoration(SearchActivity.this, LinearLayoutManager.VERTICAL));
        rvSearch.setAdapter(wordAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_tool_menu, menu);

        menu.findItem(R.id.mnSearchTool).expandActionView();
        SearchView searchView = (SearchView) menu.findItem(R.id.mnSearchTool).getActionView();

        searchView.setIconified(true);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                wordAdapter.getFilter().filter(query);
                if(wordAdapter.getItemCount() > 0){
                    tvSearch.setVisibility(View.GONE);
                }else {
                    tvSearch.setVisibility(View.VISIBLE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                wordAdapter.getFilter().filter(newText);
                if(wordAdapter.getItemCount() > 0){
                    tvSearch.setVisibility(View.GONE);
                } else if (newText.isEmpty()) {
                    tvSearch.setVisibility(View.VISIBLE);
                }else {
                    tvSearch.setVisibility(View.GONE);
                }
                return false;
            }
        });

        return true;
    }

    @Override
    public void OnWordClick(Word word) {
        Intent iOpenDetailWord = new Intent(SearchActivity.this, WordDetailActivitty.class);
        iOpenDetailWord.putExtra("word", word);
        startActivity(iOpenDetailWord);
    }
}