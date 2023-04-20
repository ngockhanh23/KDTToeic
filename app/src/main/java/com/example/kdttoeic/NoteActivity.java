package com.example.kdttoeic;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.adapter.NoteAdapter;
import com.example.kdttoeic.model.Note;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity implements NoteAdapter.Listener {

    RecyclerView rvNotes;
    ArrayList<Note> notes;
    NoteAdapter noteAdapter;
    KDTToeicDB kdtToeicDB;
    int index;
    //Cách 1
    //Đối tượng vừa gửi dữ liệu đi và vừa nhận dữ liệu về
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == 2003) {
                Note note = (Note) result.getData().getSerializableExtra("note");
                kdtToeicDB.updateNote(note.getId(), note.getTitle(), note.getContent());
                notes.clear();
                notes.addAll(kdtToeicDB.getNote());
                noteAdapter.notifyDataSetChanged();
            }
            if (result.getResultCode() == 2004) {
                Note note = (Note) result.getData().getSerializableExtra("note");
                kdtToeicDB.insertNote(note.getTitle(), note.getContent());
                notes.clear();
                notes.addAll(kdtToeicDB.getNote());
                noteAdapter.notifyDataSetChanged();
            }
        }
    });

    //Tạo menu add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_add_menu, menu);
        return true;
    }


    //Khi click vào add ghi chú
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnAdd) {
            Intent intent = new Intent(NoteActivity.this, NoteAddActivity.class);
            launcher.launch(intent);
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        rvNotes = findViewById(R.id.rvNotes);
        kdtToeicDB = new KDTToeicDB(NoteActivity.this);
        notes = kdtToeicDB.getNote();

        noteAdapter = new NoteAdapter(notes, this);
        rvNotes.setAdapter(noteAdapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvNotes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getSupportActionBar().setTitle("Ghi chú");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void OnEditClick(int pos, Note note) {
        index = pos;
        Intent intent = new Intent(NoteActivity.this, NoteEditActivity.class);
        intent.putExtra("note", note);
        launcher.launch(intent);
    }

    @Override
    public void OnDeleteNote(int pos, Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
        builder.setTitle("Bạn có chắc chắn muốn xóa: \n" + note.getTitle());
        builder.setMessage(note.getContent());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                kdtToeicDB.deleteNote(note.getId());
                notes.clear();
                notes.addAll(kdtToeicDB.getNote());
                noteAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}