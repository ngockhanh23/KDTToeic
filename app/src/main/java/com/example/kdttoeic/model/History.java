package com.example.kdttoeic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class History {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("amountQuestion")
    @Expose
    private int amountQuestion;
    @SerializedName("maxAmountQuestion")
    @Expose
    private int maxAmountQuestion;
    @SerializedName("score")
    @Expose
    private float Score;

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
