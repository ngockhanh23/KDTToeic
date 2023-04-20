package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kdttoeic.model.Note;

public class NoteAddActivity extends AppCompatActivity {

    EditText etTitle, etContent;
    Button btSave, btCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btSave = findViewById(R.id.btSave);
        btCancle = findViewById(R.id.btCancle);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitle.length() == 0 || etContent.length() == 0) {
                    Toast.makeText(NoteAddActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Note note = new Note( etTitle.getText().toString()
                            , etContent.getText().toString());
                    Intent intent = getIntent();
                    intent.putExtra("note", note);
                    setResult(2004, intent);
                    finish();
                }
            }
        });
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
