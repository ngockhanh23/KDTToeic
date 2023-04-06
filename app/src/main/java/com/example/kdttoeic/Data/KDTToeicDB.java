package com.example.kdttoeic.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kdttoeic.model.History;
import com.example.kdttoeic.model.HistoryDetails;
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
            tmp.add(question);
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

    //Lấy lịch sử làm bài
    public ArrayList<History> getHistory(){
        ArrayList<History> lstTemp = new ArrayList<>();
        String sql = "SELECT * FROM tblHistory";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String topic = cursor.getString(1);
            int amountQuestion = cursor.getInt(2);
            int maxAmountQuestion = cursor.getInt(3);
            float score = cursor.getFloat(4);
            History history = new History(id,topic,amountQuestion, maxAmountQuestion, score);
            lstTemp.add(history);
        }
        db.close();
        return lstTemp;
    }

    //Thêm lịch sử làm bài
    public void insertHistory(String topic, int amountQuestion, int maxAmountQuestion, float score){
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("TOPIC", topic);
        cv.put("AMOUNT_QUESTION", amountQuestion);
        cv.put("MAX_AMOUNT_QUESTION", maxAmountQuestion);
        cv.put("SCORE", score);

        db.insert("tblHistory", null, cv);
        db.close();
    }


    //Cập nhật lịch sử
    public void updateHistory(int id, int amountQuestion,int maxAmountQuestion, float score) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("AMOUNT_QUESTION", amountQuestion);
        cv.put("MAX_AMOUNT_QUESTION", maxAmountQuestion);
        cv.put("SCORE", score);
        db.update("tblHistory", cv, "ID=" + id, null);
        db.close();
    }

    //xóa lịch sử
    public void deleteHistory(int id) {
        db = openDB();
        db.delete("tblHistory", "ID=" + id, null);
        db.close();
    }

    //xóa toàn bộ lịch sử
    public void clearHistory() {
        db = openDB();
        db.delete("tblHistory", "", null);
        db.close();
    }

    //Lấy số lượng lịch sử bài làm
    public int countHistory(){
        String sql = "SELECT * FROM tblHistory";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        return cursor.getCount();
    }

    //Load danh sách đáp án theo lịch sử kiểm tra
    public ArrayList<HistoryDetails> getAnswerList(int idHistory){
        ArrayList<HistoryDetails> lstAnswer = new ArrayList<>();
//        String sql = "SELECT * FROM tblHistoryDetails WHELE ID_HISTORY = " + String.valueOf(idHistory)  ;
//        String sql = "SELECT * FROM tblHistoryDetails" ;
        String sql = "SELECT * FROM tblHistoryDetails where ID_HISTORY ="+idHistory;

        db = openDB();
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int id_History = cursor.getInt(1);
            int selectOptionUser = cursor.getInt(2);
            int correctAnswer = cursor.getInt(3);
            HistoryDetails historyDetails = new HistoryDetails(id,id_History,selectOptionUser, correctAnswer);
            lstAnswer.add(historyDetails);
        }
        db.close();
        return lstAnswer;
    }

    //Thêm chi tiết đáp án
    public void insertHistoryDetails(int idHistory, int selectOptionUser, int correctAnswer){
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("ID_HISTORY", idHistory);
        cv.put("SELECTED_OPTION_USER", selectOptionUser);
        cv.put("CORRECT_ANSWER", correctAnswer);
        db.insert("tblHistoryDetails", null, cv);
        db.close();
    }

    //Xóa chi tiết đáp án

    public void deleteHistoryDetails(int id) {
        db = openDB();
        db.delete("tblHistoryDetails", "ID_History=" + id, null);
        db.close();
    }

    //Xóa toàn bộ chi tiết đáp án
    public void clearHistoryDetails() {
        db = openDB();
        db.delete("tblHistoryDetails", "", null);
        db.close();
    }
}