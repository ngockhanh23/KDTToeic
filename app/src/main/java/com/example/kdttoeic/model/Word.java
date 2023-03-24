package com.example.kdttoeic.model;

import java.io.Serializable;

public class Word implements Serializable {
    int id;
    String En;
    String Ve;

    public Word(int id, String en, String ve) {
        this.id = id;
        En = en;
        Ve = ve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEn() {
        return En;
    }

    public void setEn(String en) {
        En = en;
    }

    public String getVe() {
        return Ve;
    }

    public void setVe(String ve) {
        Ve = ve;
    }
}
