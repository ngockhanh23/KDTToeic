package com.example.kdttoeic.model;

import java.io.Serializable;

public class Question implements Serializable {
    int id;
    String content;
    String image;
    String audio;
    String opA;
    String opB;
    String opC;
    String opD;
    int answer;
    int level;
    int love;
    int questionCat;


    public Question(int id, String content, String image, String audio, String opA, String opB, String opC, String opD, int answer, int level, int love,int questionCat) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.audio = audio;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.answer = answer;
        this.level = level;
        this.questionCat = questionCat;
        this.love = love;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getOpA() {
        return opA;
    }

    public void setOpA(String opA) {
        this.opA = opA;
    }

    public String getOpB() {
        return opB;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getOpD() {
        return opD;
    }

    public void setOpD(String opD) {
        this.opD = opD;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getQuestionCat() {
        return questionCat;
    }

    public void setQuestionCat(int questionCat) {
        this.questionCat = questionCat;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }


}

