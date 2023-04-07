package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.model.Question;

public class DetailsAnswerActivity extends AppCompatActivity {

    TextView tvDetailsAnswerQuestion,tvDetailOptionA, tvDetailOptionB,tvDetailOptionC, tvDetailOptionD;
    ImageView ivDetailOptionA, ivDetailOptionB, ivDetailOptionC, ivDetailOptionD;
    Button btBackDetailsHistory;
    KDTToeicDB kdtToeicDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_answer);
        AnhXa();
        kdtToeicDB = new KDTToeicDB(DetailsAnswerActivity.this);
        Bundle bundle = getIntent().getExtras();


        Question question = kdtToeicDB.getQuestionItem(bundle.getInt("ID_Question"));
        tvDetailsAnswerQuestion.setText(question.getContent().toString());
        tvDetailOptionA.setText(question.getOpA());
        tvDetailOptionB.setText(question.getOpB());
        tvDetailOptionC.setText(question.getOpC());
        tvDetailOptionD.setText(question.getOpD());


        switch (bundle.getInt("selectOptionUser")){
            case 1: ivDetailOptionA.setImageResource(R.drawable.a_wrong_answer); break;
            case 2: ivDetailOptionB.setImageResource(R.drawable.b_wrong_answer); break;
            case 3: ivDetailOptionC.setImageResource(R.drawable.c_wrong_answer); break;
            case 4: ivDetailOptionD.setImageResource(R.drawable.d_wrong_answer); break;
        }

        switch (bundle.getInt("correctAnswer")){
            case 1: ivDetailOptionA.setImageResource(R.drawable.a_correct_answer); break;
            case 2: ivDetailOptionB.setImageResource(R.drawable.b_correct_answer); break;
            case 3: ivDetailOptionC.setImageResource(R.drawable.c_correct_answer); break;
            case 4: ivDetailOptionD.setImageResource(R.drawable.d_correct_answer); break;
        }

        btBackDetailsHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void AnhXa(){
        tvDetailsAnswerQuestion = findViewById(R.id.tvDetailsAnswerQuestion);
        tvDetailOptionA = findViewById(R.id.tvDetailOptionA);
        tvDetailOptionB = findViewById(R.id.tvDetailOptionB);
        tvDetailOptionC = findViewById(R.id.tvDetailOptionC);
        tvDetailOptionD = findViewById(R.id.tvDetailOptionD);
        ivDetailOptionA = findViewById(R.id.ivDetailOptionA);
        ivDetailOptionB = findViewById(R.id.ivDetailOptionB);
        ivDetailOptionC = findViewById(R.id.ivDetailOptionC);
        ivDetailOptionD = findViewById(R.id.ivDetailOptionD);
        btBackDetailsHistory = findViewById(R.id.btBackDetailsHistory);
    }
}