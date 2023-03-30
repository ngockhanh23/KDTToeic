package com.example.kdttoeic.model;


public class History {

    int id;
    String topic;
    int amountQuestion;
    int maxAmountQuestion;
    float Score;

    public History(int id, String topic, int amountQuestion, int maxAmountQuestion, float score) {
        this.id = id;
        this.topic = topic;
        this.amountQuestion = amountQuestion;
        this.maxAmountQuestion = maxAmountQuestion;
        Score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getAmountQuestion() {
        return amountQuestion;
    }

    public void setAmountQuestion(int amountQuestion) {
        this.amountQuestion = amountQuestion;
    }

    public int getMaxAmountQuestion() {
        return maxAmountQuestion;
    }

    public void setMaxAmountQuestion(int maxAmountQuestion) {
        this.maxAmountQuestion = maxAmountQuestion;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }


}
