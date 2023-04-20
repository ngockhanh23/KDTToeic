package com.example.kdttoeic.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class HistoryList {
    @SerializedName("history")
    @Expose
    private ArrayList<History> history;

    public HistoryList() {
    }

    public HistoryList(ArrayList<History> history) {
        super();
        this.history = history;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

}
