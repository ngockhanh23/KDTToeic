package com.example.kdttoeic.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kdttoeic.model.Note;
import com.example.kdttoeic.model.Question;
import com.example.kdttoeic.model.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class KDTToeicDB {
    //Database First
    String dbName = "KDTToeic.db";
    //Giúp tạo ra CSDL
    Context context;
    SQLiteDatabase db;

    public KDTToeicDB(Context context) {
        this.context = context;
    }

    //code first
    //Hàm này sinh ra CSDL
    public SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    public void copyDatabase() {
        //data/data/package_name/databases/StudentDB.db
        File dbFile = context.getDatabasePath(dbName);
        if (!dbFile.exists()) {
            try {
                InputStream is = context.getAssets().open(dbName);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0) {
                    os.write(buffer);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
    }

    //lấy từ vựng
    public ArrayList<Word> getVocab() {
        ArrayList<Word> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tblVocabulary";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String en = cursor.getString(1);
            String ve = cursor.getString(2);
            String spell = cursor.getString(3);
            int love = cursor.getInt(4);
            String example = cursor.getString(5);
            String image = cursor.getString(6);
            int vocabCat = cursor.getInt(7);
            Word word = new Word(id, en, ve, spell, love, example, image, vocabCat);
            tmp.add(word);
        }
        return tmp;
    }

    //lấy câu hỏi
    public ArrayList<Question> getQuestion() {
        ArrayList<Question> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tblQuestion";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String content = cursor.getString(1);
            String image = cursor.getString(2);
            String audio = cursor.getString(3);
            String opA = cursor.getString(4);
            String opB = cursor.getString(5);
            String opC = cursor.getString(6);
            String opD = cursor.getString(7);
            int asnwer = cursor.getInt(8);
            int level = cursor.getInt(9);
            int love = cursor.getInt(10);
            int questionCat = cursor.getInt(11);
            Question question = new Question(id, content, image, audio, opA, opB, opC, opD, asnwer, level, love, questionCat);
        }
        return tmp;
    }

    //lấy ghi chú
    public ArrayList<Note> getNote() {
        ArrayList<Note> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tblNote";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            Note note = new Note(id, title, content);
            tmp.add(note);
        }
        db.close();
        return tmp;
    }

    //Thêm ghi chú
    public void insertNote(String title, String content) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("TITLE", title);
        cv.put("CONTENT", content);
        db.insert("tblNote", null, cv);
        db.close();
    }

    //Cập nhật ghi chú
    public void updateNote(int id, String title, String content) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("TITLE", title);
        cv.put("CONTENT", content);
        db.update("tblNote", cv, "ID=" + id, null);
        db.close();
    }

    //Xóa ghi chú
    public void deleteNote(int id) {
        db = openDB();
        db.delete("tblNote", "ID=" + id, null);
        db.close();
    }
}
