package com.example.kdttoeic.model;

import java.io.Serializable;

public class Word implements Serializable, Comparable<Word> {
    int id;
    String En;
    String Ve;
    String Spell;
    int love;
    String example;
    String image;
    int vocabCat;

    public Word() {

    }

    public Word(int id, String en, String ve, String spell, int love, String example, String image, int vocabCat) {
        this.id = id;
        En = en;
        Ve = ve;
        Spell = spell;
        this.love = love;
        this.example = example;
        this.image = image;
        this.vocabCat = vocabCat;
    }

    public int getVocabCat() {
        return vocabCat;
    }

    public void setVocabCat(int vocabCat) {
        this.vocabCat = vocabCat;
    }

    public int getId() {
        return id;
    }

    public String getSpell() {
        return Spell;
    }

    public void setSpell(String spell) {
        Spell = spell;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public int compareTo(Word o) {
        return En.compareTo(o.getEn());
    }
}
