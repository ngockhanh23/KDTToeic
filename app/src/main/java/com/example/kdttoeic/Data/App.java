package com.example.kdttoeic.Data;

import android.app.Application;

public class App extends Application {

    KDTToeicDB kdtToeicDB;

    @Override
    public void onCreate() {
        super.onCreate();
        kdtToeicDB = new KDTToeicDB(this);
        kdtToeicDB.copyDatabase();
    }
}
