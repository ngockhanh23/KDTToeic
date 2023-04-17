package com.example.kdttoeic.model;

public class Test {
    private String title;
    private String description;
    private String mucde;

    public Test(String title, String description, String mucde) {
        this.title = title;
        this.description = description;
        this.mucde = mucde;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMucde() {
        return mucde;
    }

    public void setMucde(String mucde) {
        this.mucde = mucde;
    }
}
