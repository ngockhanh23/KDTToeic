package com.example.kdttoeic.model;

public class HistoryDetails {
    public HistoryDetails(int id, int idHistory, int selectedOptionUser, int correctAnswer) {
        this.id = id;
        this.idHistory = idHistory;
        this.selectedOptionUser = selectedOptionUser;
        this.correctAnswer = correctAnswer;
    }
    int id;
    int idHistory;
    int selectedOptionUser;
    int correctAnswer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public int getSelectedOptionUser() {
        return selectedOptionUser;
    }

    public void setSelectedOptionUser(int selectedOptionUser) {
        this.selectedOptionUser = selectedOptionUser;
    }

    public int getcorrectAnswer() {
        return correctAnswer;
    }

    public void setcorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
