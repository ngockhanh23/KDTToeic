package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btLogin;
    TextView tvRegisterLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("ĐĂNG NHẬP");
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        //btLogin = findViewById(R.id.btLogin);
//        btLogin.setOnClickListener(new View.OnClickListener() {
//            boolean isEmpty = etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty();
//            @Override
//            public void onClick(View v) {
//                if(isEmpty){
//                    Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

        tvRegisterLink = findViewById(R.id.tvRegisterLink);
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}