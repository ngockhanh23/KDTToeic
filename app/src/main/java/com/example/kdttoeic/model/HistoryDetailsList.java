package com.example.kdttoeic.model;

import java.util.ArrayList;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class HistoryDetailsList {
    @SerializedName("historyDetails")
    @Expose
    private ArrayList<HistoryDetails> historyDetails;

    public HistoryDetailsList() {
    }

    public HistoryDetailsList(ArrayList<HistoryDetails> history) {
        super();
        this.historyDetails = history;
    }

    public ArrayList<HistoryDetails> getHistoryDetails() {
        return historyDetails;
    }

    public void setHistoryDetails(ArrayList<HistoryDetails> historyDetails) {
        this.historyDetails = historyDetails;
    }

}
