package com.example.kdttoeic.model;

public class HistoryDetails {
    public HistoryDetails(int id, int idHistory, int selectedOptionUser, int correctAnswer, int idQuestion) {
        this.id = id;
        this.idHistory = idHistory;
        this.selectedOptionUser = selectedOptionUser;
        this.correctAnswer = correctAnswer;
        this.idQuestion = idQuestion;
    }
    int id;
    int idHistory;
    int selectedOptionUser;
    int correctAnswer;

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    int idQuestion;

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




}
