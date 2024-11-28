package com.example.proswim;

public class Exercise {
    private String title;
    private int videoResourceId;

    // Construtor
    public Exercise(String title, int videoResourceId) {
        this.title = title;
        this.videoResourceId = videoResourceId;
    }

    public String getTitle() {
        return title;
    }

    public int getVideoResourceId() {
        return videoResourceId;
    }
}

