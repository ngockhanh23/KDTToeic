package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone, etPassword,etConfrimPass;
    Button btRegis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("ĐĂNG NHẬP");
        anhxa();
        btRegis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isempty = etName.getText().toString().isEmpty()  || etEmail.getText().toString().isEmpty() && etPhone.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()|| etConfrimPass.getText().toString().isEmpty();
                if(isempty){
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }
                else if(etPassword.getText().toString().compareTo(etConfrimPass.getText().toString()) == 1 ){
                    Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void anhxa() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfrimPass = findViewById(R.id.etConfrimPass);
        btRegis = findViewById(R.id.btRegis);
    }
}