package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kdttoeic.model.Question;

import java.util.ArrayList;
import java.util.Collections;

public class PracticeActivity extends AppCompatActivity {

    TextView tvQuestionPractice;
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
        AnhXa();
        AddQuestion();

        Collections.shuffle(lstQuestion);

        tvQuestionPractice.setText(lstQuestion.get(0).getContent());
        rbOpA.setText(lstQuestion.get(0).getOpA());
        rbOpB.setText(lstQuestion.get(0).getOpB());
        rbOpC.setText(lstQuestion.get(0).getOpC());
        rbOpD.setText(lstQuestion.get(0).getOpD());

        correctAnswer = lstQuestion.get(0).getAnswer();


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


        //xử lý sự kiện khi click sang câu hỏi tiếp theo
        btNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionUser == correctAnswer){
                    countCorrectAnswer++;
                }


                if(count == maxAmountQuestion)
                {
                    Intent intent = new Intent(PracticeActivity.this, ResultTestActivity.class);
                    intent.putExtra("countCorrectAnswer", countCorrectAnswer);
                    intent.putExtra("maxAmountQuestion", maxAmountQuestion);
                    startActivity(intent);
                    finish();
                }

//                Toast.makeText(PracticeActivity.this,optionUser,Toast.LENGTH_LONG).show();

               else{
                    Intent i = getIntent();

                    i.putExtra("question", lstQuestion.get(count).getContent());
                    i.putExtra("opA", lstQuestion.get(count).getOpA());
                    i.putExtra("opB", lstQuestion.get(count).getOpB());
                    i.putExtra("opC", lstQuestion.get(count).getOpC());
                    i.putExtra("opD", lstQuestion.get(count).getOpD());
                    i.putExtra("answer", lstQuestion.get(count).getAnswer());


                    Bundle nextQuestion = getIntent().getExtras();
                    rgOptionsQuestion.clearCheck();
                    tvQuestionPractice.setText(nextQuestion.getString("question"));
                    rbOpA.setText(nextQuestion.getString("opA"));
                    rbOpB.setText(nextQuestion.getString("opB"));
                    rbOpC.setText(nextQuestion.getString("opC"));
                    rbOpD.setText(nextQuestion.getString("opD"));
                    correctAnswer = nextQuestion.getInt("answer");
                    count++;
                }


            }
        });


        tvQuestionPractice.setText(lstQuestion.get(0).getContent().toString());

        getSupportActionBar().setTitle("Luyện tập");

    }

    void AnhXa(){
        tvQuestionPractice = findViewById(R.id.tvQuestionPractice);
        rgOptionsQuestion = findViewById(R.id.rgOptionsQuestion);
        rbOpA = findViewById(R.id.rbOpA);
        rbOpB = findViewById(R.id.rbOpB);
        rbOpC = findViewById(R.id.rbOpC);
        rbOpD = findViewById(R.id.rbOpD);
        btNextQuestion = findViewById(R.id.btNextQuestion);
    }

    void AddQuestion(){
        lstQuestion = new ArrayList<>();
        lstQuestion.add(new Question("Who are all ________ people?", "this","those","them","that",2));
        lstQuestion.add(new Question("I ____ a car next year", "buy","am buying","going to buy","bought",2));
        lstQuestion.add(new Question("When do you go _____ bed?", "to","to the","in","in the",1));
        lstQuestion.add(new Question("London is famous for _____ red buses", "it's","its","it","is it",2));
        lstQuestion.add(new Question("Is there _____ milk in the fridge?", "a lot ","many","much","some",4));



    }
}