package com.example.kdttoeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.switchmaterial.SwitchMaterial;

import fragment.HomeFragment;
import fragment.SearchFragment;
import fragment.SettingFragment;
import fragment.TestFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottommNav;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottommNav = findViewById(R.id.bottommNav);

        //load lên Fragment
        loadFragment(new HomeFragment());
        actionBar = getSupportActionBar();
        actionBar.setTitle("Lộ trình");

        bottommNav.setOnItemSelectedListener(getListener());
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnHome:
                        actionBar.setTitle(item.getTitle().toString());
                        loadFragment(new HomeFragment());
                        return true;
                    case R.id.mnTest:
                        actionBar.setTitle(item.getTitle().toString());
                        loadFragment(new TestFragment());
                        return true;
                    case R.id.mnSearch:
                        actionBar.setTitle(item.getTitle().toString());
                        loadFragment(new SearchFragment());
                        return true;
                    case R.id.mnSetting:
                        actionBar.setTitle(item.getTitle().toString());
                        loadFragment(new SettingFragment());
                        return true;
                }

                return true;
            }
        };
    }

    void loadFragment(Fragment fmNew) {
        //Gọi quản lí Fragment để tiến hành thay đổi Fragment
        FragmentTransaction fmCur = getSupportFragmentManager().beginTransaction();
        //Thay thế Fragment hiện tại = Fragment mới vào id.container là Framelayout cu của màn hình hiện Fragment
        fmCur.replace(R.id.container, fmNew);
        //Thực hiện việc chuyển đổi
        fmCur.commit();
    }


}