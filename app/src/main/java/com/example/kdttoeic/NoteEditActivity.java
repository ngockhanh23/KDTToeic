package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kdttoeic.model.Note;

public class NoteEditActivity extends AppCompatActivity {

    EditText etTitle, etContent;
    Button btSave, btCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btSave = findViewById(R.id.btSave);
        btCancle = findViewById(R.id.btCancle);

        Intent intent = getIntent();
        Note note = (Note) intent.getSerializableExtra("note");

        etTitle.setText(note.getTitle());
        etContent.setText(note.getContent());
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note1 = new Note(note.getId(), etTitle.getText().toString(),
                        etContent.getText().toString());
                //Gửi intent vào trong hệ thống tk nào đang nhận dữ liệu thì chụp lấy nó mà nhận
                Intent intent1 = getIntent();
                intent1.putExtra("note", note1);
                setResult(2003, intent1);
                finish();
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