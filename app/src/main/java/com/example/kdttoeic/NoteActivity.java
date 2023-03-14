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

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity implements NoteAdapter.Listener {

    RecyclerView rvNotes;
    ArrayList<Note> notes;
    NoteAdapter noteAdapter;
    int index;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == 2003) {
                Note note = (Note) result.getData().getSerializableExtra("note");
                notes.set(index, note);
                noteAdapter.notifyDataSetChanged();
            }
            if (result.getResultCode() == 2004) {
                Note note = (Note) result.getData().getSerializableExtra("note");
                notes.add(note);
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
        //Thoát trang
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

        notes = new ArrayList<>();
        Note note = new Note(1, "Khánh ròm", "Tuổi con ngang con");
        notes.add(note);
        note = new Note(2, "Khải", "Trong cơn mơ a là chàng tỉ phú, tỉnh cơn lú anh là chú báo con");
        notes.add(note);
        note = new Note(3, "Đức", "Lớn già đầu còn bị dụ mất acc");
        notes.add(note);
        note = new Note(4, "Triều", "trieudeptrai123 đến là đón đụng là chạm");
        notes.add(note);

        noteAdapter = new NoteAdapter(notes, this);
        rvNotes.setAdapter(noteAdapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvNotes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getSupportActionBar().setTitle("Ghi chú");
        //Hiệ dấu mũi tên quay về
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
                notes.remove(pos);
                noteAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}