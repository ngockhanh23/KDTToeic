package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import fragment.SettingFragment;

public class WaitingScreenActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);
        getSupportActionBar().hide();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Chuyển từ màn hình chờ sang màn hình menu
                Intent iMenu = new Intent(WaitingScreenActivity.this, MainActivity.class);
                startActivity(iMenu);
                //Kết thúc màn hình flashscreen để user kh back lại đc
                finish();
            }
        }, 2000);
    }
}