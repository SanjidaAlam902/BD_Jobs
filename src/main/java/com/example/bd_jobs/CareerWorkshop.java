package com.example.bd_jobs;

public class CareerWorkshop {
    private String title;
    private String date;
    private int capacity;
    private String description;

    private String candidateName;
    private String recommendedRole;
    private String requiredTraining;

    public CareerWorkshop(String title, String date, int capacity, String description) {
        this.title = title;
        this.date = date;
        this.capacity = capacity;
        this.description = description;
    }

    public CareerWorkshop(String candidateName, String recommendedRole, String requiredTraining) {
        this.candidateName = candidateName;
        this.recommendedRole = recommendedRole;
        this.requiredTraining = requiredTraining;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public int getCapacity() { return capacity; }
    public String getDescription() { return description; }

    public String getCandidateName() { return candidateName; }
    public String getRecommendedRole() { return recommendedRole; }
    public String getRequiredTraining() { return requiredTraining; }
}
