package com.example.kdttoeic.Data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class App extends Application {

    KDTToeicDB kdtToeicDB;
    String filename = "config";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean nightMode;

    @Override
    public void onCreate() {
        super.onCreate();
        kdtToeicDB = new KDTToeicDB(this);
        kdtToeicDB.copyDatabase();

        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        nightMode = sharedPreferences.getBoolean("night", false);
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        editor.commit();
    }

}
