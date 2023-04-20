package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.model.History;
import com.example.kdttoeic.model.Question;

import java.util.ArrayList;
import java.util.Collections;

public class PracticeActivity extends AppCompatActivity {

    KDTToeicDB kdtToeicDB;
    TextView tvQuestionPractice, tvAmountQuestionTest, tvMaxAmountQuestionTest;
    RadioGroup rgOptionsQuestion;
    RadioButton rbOpA, rbOpB, rbOpC, rbOpD;
    Button btNextQuestion;


    ArrayList<Question> lstQuestion;
    private int maxAmountQuestion;
    private int count = 1; //vị trí câu hỏi
    private int countCorrectAnswer = 0;  // số câu đúng
    private int correctAnswer ;
    private int optionUser = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        kdtToeicDB = new KDTToeicDB(PracticeActivity.this);
        AnhXa();
        AddQuestion();

//        Collections.shuffle(lstQuestion);



        tvQuestionPractice.setText(lstQuestion.get(0).getContent());
        rbOpA.setText(lstQuestion.get(0).getOpA());
        rbOpB.setText(lstQuestion.get(0).getOpB());
        rbOpC.setText(lstQuestion.get(0).getOpC());
        rbOpD.setText(lstQuestion.get(0).getOpD());

        correctAnswer = lstQuestion.get(0).getAnswer();

        History lastHistory = kdtToeicDB.lastHistory();

        //Bắt sự kiện người dùng chọn đáp án
        rgOptionsQuestion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbOpA:
                        optionUser = 1;
                        break;
                    case R.id.rbOpB:
                        optionUser = 2;
                        break;
                    case R.id.rbOpC:
                        optionUser = 3;
                        break;
                    case R.id.rbOpD:
                        optionUser = 4;
                        break;
                }
            }
        });

        //lấy số lượng câu hỏi
        Bundle bundle = getIntent().getExtras();
        maxAmountQuestion = Integer.parseInt(bundle.getString("maxAmountQuestion"));
        tvMaxAmountQuestionTest.setText(String.valueOf(this.maxAmountQuestion));


        //xử lý sự kiện khi click sang câu hỏi tiếp theo
        btNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionUser == correctAnswer){
                    countCorrectAnswer++;
                }


                if(count == maxAmountQuestion)
                {

                    kdtToeicDB.insertHistoryDetails(lastHistory.getId(),optionUser,correctAnswer,lstQuestion.get(count-1).getId());

                    Intent intent = new Intent(PracticeActivity.this, ResultTestActivity.class);
                    intent.putExtra("countCorrectAnswer", countCorrectAnswer);
                    intent.putExtra("maxAmountQuestion", maxAmountQuestion);
//                    intent.putExtra("ID_HISTORY",countHistory);
                    float score =  (float) countCorrectAnswer / (float) maxAmountQuestion * 100;

                    //Cập nhật lịch sử
                    kdtToeicDB.updateHistory(lastHistory.getId(),countCorrectAnswer,maxAmountQuestion,score);

                    startActivity(intent);
                    finish();
                }

//                Toast.makeText(PracticeActivity.this,optionUser,Toast.LENGTH_LONG).show();

               else{
//                  thêm lịch sử đáp án
                    if(count == 1){
                        kdtToeicDB.insertHistoryDetails(lastHistory.getId(),optionUser,correctAnswer,lstQuestion.get(0).getId());
                    }
                    else {
                        kdtToeicDB.insertHistoryDetails(lastHistory.getId(),optionUser,correctAnswer,lstQuestion.get(count-1).getId());
                    }


                    rgOptionsQuestion.clearCheck();

                    tvQuestionPractice.setText(lstQuestion.get(count).getContent());
                    rbOpA.setText(lstQuestion.get(count).getOpA());
                    rbOpB.setText(lstQuestion.get(count).getOpB());
                    rbOpC.setText(lstQuestion.get(count).getOpC());
                    rbOpD.setText(lstQuestion.get(count).getOpD());
                    correctAnswer = lstQuestion.get(count).getAnswer();
                    count++;
                    tvAmountQuestionTest.setText(String.valueOf(count));

                    
               }


            }
        });


//        tvQuestionPractice.setText(lstQuestion.get(0).getContent().toString());

        getSupportActionBar().setTitle("Luyện tập");

    }

    void AnhXa(){
        tvAmountQuestionTest = findViewById(R.id.tvAmountQuestionTest);
        tvMaxAmountQuestionTest = findViewById(R.id.tvMaxAmountQuestionTest);
        tvQuestionPractice = findViewById(R.id.tvQuestionPractice);
        rgOptionsQuestion = findViewById(R.id.rgOptionsQuestion);
        rbOpA = findViewById(R.id.rbOpA);
        rbOpB = findViewById(R.id.rbOpB);
        rbOpC = findViewById(R.id.rbOpC);
        rbOpD = findViewById(R.id.rbOpD);
        btNextQuestion = findViewById(R.id.btNextQuestion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mnInflater = getMenuInflater();
        mnInflater.inflate(R.menu.exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnExit){
            ExitButtonOption();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void ExitButtonOption(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PracticeActivity.this);
        builder.setTitle("Oh no !!!");
        builder.setMessage("Nếu bạn thoát, tiến độ làm bài của bạn sẽ không được lưu lại, bạn có chắc chắn muốn thoát hay không ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                History lastHistory = kdtToeicDB.getHistory().get(kdtToeicDB.countHistory()-1);
                kdtToeicDB.deleteHistoryDetails(lastHistory.getId());
                kdtToeicDB.deleteHistory(lastHistory.getId());
                finish();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.show();
        alertDialog.show();
    }

    void AddQuestion(){

        lstQuestion = kdtToeicDB.getQuestion();

    }
}